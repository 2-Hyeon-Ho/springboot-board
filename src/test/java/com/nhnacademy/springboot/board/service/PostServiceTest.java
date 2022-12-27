package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.Role;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.repository.post.PostRepository;
import com.nhnacademy.springboot.board.repository.role.RoleRepository;
import com.nhnacademy.springboot.board.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;



    Role role;
    User user;
    Post post;

    @BeforeEach
    void setUp() {
        role = new Role(1, "admin");
//        roleRepository.save(role);
        user = new User(999, "lee", "123", LocalDateTime.now(), role);
//        userRepository.save(user);
        post = new Post(999, null, user, null, "test", "hi", LocalDateTime.now(), null, 2, null);
//        postRepository.save(post);
    }

    @Test
    void registerPost() {
//        PostDto postDto = PostDto.create(post);

//        given(postRepository.save(post)).
//                willReturn(post);
        when(postRepository.save(any())).thenReturn(post);

        PostDto dto = postService.registerPost(user,
                post.getTitle(),
                post.getContent());

        assertThat(dto.getContent()).isEqualTo("hi");
        verify(postRepository,times(1)).save(any());

    }

    @Test
    void modifyPost() {
        Post newPost =
                new Post(999, null, user, user, "hi2", "test2", post.getCreatedAt(), LocalDateTime.now(), post.getDeleteFlag(), null);
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        when(postRepository.save(any())).thenReturn(newPost);

        postService.modifyPost(newPost.getModifier(), newPost.getTitle(), newPost.getContent(), newPost.getPostId());

        verify(postRepository, times(1)).findById(any());

        verify(postRepository, times(1)).save(any());

    }

    @Test
    void adminModifyPost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void adminDeletePost() {
    }

    @Test
    void restorePost() {
    }

    @Test
    void getPost() {
//        postService.getPost(post.getPostId());
    }

    @Test
    void getPosts() {
    }

    @Test
    void getDeletePosts() {
    }
}