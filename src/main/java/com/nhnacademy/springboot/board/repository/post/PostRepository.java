package com.nhnacademy.springboot.board.repository.post;


import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {
}
