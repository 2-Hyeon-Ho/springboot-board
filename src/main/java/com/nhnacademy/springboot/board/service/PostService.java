package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.exception.NotAuthorityException;
import com.nhnacademy.springboot.board.exception.PostNotFoundException;
import com.nhnacademy.springboot.board.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostDto registerPost(User writer, String title, String content) {
        Post post = Post.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .deleteFlag(1)
                .build();


        Post save = postRepository.save(post);


        return PostDto.create(save);
    }

    @Transactional
    public void modifyPost(User user, String title, String content, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        if(!post.getWriter().getUserId().equals(user.getUserId())) {

            throw new NotAuthorityException();
        }
        Post modifyPost = post.modify(user, title, content);

        postRepository.save(modifyPost);

    }

    @Transactional
    public void adminModifyPost(User user, String title, String content, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        Post modifyPost = post.modify(user, title, content);

        postRepository.save(modifyPost);
    }

    @Transactional
    public void deletePost(int userId, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        if(post.getWriter().getUserId().equals(userId)) {

            postRepository.save(post.delete());
        }
    }

    @Transactional
    public void adminDeletePost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        postRepository.save(post.delete());
    }

    @Transactional
    public void restorePost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        postRepository.save(post.restore());
    }

    @Transactional(readOnly = true)
    public PostDto getPost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        if(Objects.isNull(post.getModifier())) {
            return PostDto.create(post);
        }
        return PostDto.create(post);
    }

    @Transactional(readOnly = true)
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.getNoneDeletePosts(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> getDeletePosts(Pageable pageable) {
        return postRepository.getDeletePosts(pageable);
    }
}
