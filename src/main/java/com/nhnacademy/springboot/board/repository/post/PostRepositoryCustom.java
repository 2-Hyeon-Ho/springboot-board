package com.nhnacademy.springboot.board.repository.post;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PostRepositoryCustom {
    Page<Post> getNoneDeletePosts(Pageable pageable);
    Page<Post> getDeletePosts(Pageable pageable);

}
