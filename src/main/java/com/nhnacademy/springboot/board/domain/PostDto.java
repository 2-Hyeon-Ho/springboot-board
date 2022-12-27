package com.nhnacademy.springboot.board.domain;

import com.nhnacademy.springboot.board.entity.Comment;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {
    private Integer postId;
    @Nullable
    private Integer replyId;
    private String writerName;
    @Nullable
    private String modifierName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime modifiedAt;
    private Integer deleteFlag;
    @Nullable
    private List<Comment> comments;


    public static PostDto create(Post post) {
        return new PostDto(
                post.getPostId(),
                null,
                post.getWriter().getName(),
                null,
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                null,
                post.getDeleteFlag(),
                null);
    }

    public PostDto(Integer postId, @Nullable Integer replyId, String writerName, @Nullable String modifierName, String title, String content, LocalDateTime createdAt, @Nullable LocalDateTime modifiedAt, Integer deleteFlag, @Nullable List<Comment> comments) {
        this.postId = postId;
        this.replyId = replyId;
        this.writerName = writerName;
        this.modifierName = modifierName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deleteFlag = deleteFlag;
        this.comments = comments;
    }
}
