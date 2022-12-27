package com.nhnacademy.springboot.board.repository.like;

import com.nhnacademy.springboot.board.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Like.Pk> {
    Like findByUser_UserId(Integer userId);
}
