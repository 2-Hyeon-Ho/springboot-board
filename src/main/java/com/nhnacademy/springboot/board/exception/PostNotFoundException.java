package com.nhnacademy.springboot.board.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Integer postId) {
        super(postId + " is not found!");
    }
}
