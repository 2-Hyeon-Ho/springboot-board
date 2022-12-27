package com.nhnacademy.springboot.board.entity;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.domain.UserDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "reply_id")
    private Integer replyId;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "modifier_id")
    private User modifier;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleteflag")
    private Integer deleteFlag;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post modify(User user, String title, String content) {
        this.modifier = user;
        this.title = title;
        this.content = content;
        this.modifiedAt = LocalDateTime.now();

        return this;
    }

    public Post delete() {
        this.deleteFlag = 2;

        return this;
    }

    public Post restore() {
        this.deleteFlag = 1;

        return this;
    }
}
