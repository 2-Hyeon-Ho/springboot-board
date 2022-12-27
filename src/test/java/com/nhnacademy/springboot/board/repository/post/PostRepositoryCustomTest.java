package com.nhnacademy.springboot.board.repository.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryCustomTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
//        Pageable pageable = new PageImpl<>()
    }

    @Test
    void getNoneDeletePosts() {
//        postRepository.getNoneDeletePosts()
    }

    @Test
    void getDeletePosts() {
    }
}