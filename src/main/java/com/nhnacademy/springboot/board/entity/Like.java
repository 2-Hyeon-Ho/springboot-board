package com.nhnacademy.springboot.board.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Likes")
public class Like {
    @EmbeddedId
    private Pk pk;

    @Column(name = "like")
    private boolean isLike;

    @Transient
    private Long likeCount;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("postId")
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "user_id")
        private Integer userId;

        @Column(name = "post_id")
        private Integer postId;

    }
}
