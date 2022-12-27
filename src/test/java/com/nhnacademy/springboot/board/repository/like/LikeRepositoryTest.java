package com.nhnacademy.springboot.board.repository.like;

import com.nhnacademy.springboot.board.entity.Like;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.Role;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.repository.post.PostRepository;
import com.nhnacademy.springboot.board.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    void findAll() {
        //given
//        Role role = new Role()
//        User user = new User(1, "lee", "123", LocalDateTime.now(), )
        User newUser = new User(null, "lee", "123", LocalDateTime.now(), new Role(1, "admin"));
        Post newPost = new Post(1, 1, newUser, null, "test", "hi", LocalDateTime.now(), null, 2, null);

        userRepository.save(newUser);
        postRepository.save(newPost);

        User user = userRepository.findById(1).orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(1).orElseThrow(RuntimeException::new);
        Like like = new Like(new Like.Pk(user.getUserId(),post.getPostId()), true, 3L, user, post);

        likeRepository.save(like);

        Like actual = likeRepository.findByUser_UserId(user.getUserId());
        assertThat(actual.getUser()).isEqualTo(user);
        assertThat(actual.getPost()).isEqualTo(post);
    }

    @Test
    void findByUser_UserId() {
    }
}