package com.nhnacademy.springboot.board.repository.post;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.QPost;
import com.nhnacademy.springboot.board.entity.QUser;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {
    public PostRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public Page<Post> getNoneDeletePosts(Pageable pageable) {
        QPost post = QPost.post;
        QUser writer = QUser.user;
        QUser modifier = QUser.user;


        JPQLQuery<Post> select = from(post)
                .innerJoin(post.writer, writer)
                .leftJoin(post.modifier, modifier)
                .where(post.deleteFlag.eq(1))
                .orderBy(post.postId.asc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .select(post);

        List<Post> content = select.fetch();
        long count = select.fetchCount();

        return new PageImpl<>(content, pageable, count);
    }

    @Override
    public Page<Post> getDeletePosts(Pageable pageable) {
        QPost post = QPost.post;
        QUser writer = QUser.user;
        QUser modifier = QUser.user;

        JPQLQuery<Post> select = from(post)
                .innerJoin(post.writer, writer)
                .leftJoin(post.modifier, modifier)
                .where(post.deleteFlag.eq(2))
                .orderBy(post.postId.asc())
                .select(post);
        List<Post> content = select.fetch();
        long count = select.fetchCount();

        return new PageImpl<>(content, pageable, count);
    }
}
