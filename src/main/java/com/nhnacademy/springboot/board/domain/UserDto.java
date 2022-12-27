package com.nhnacademy.springboot.board.domain;


import com.nhnacademy.springboot.board.entity.Role;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDto {
    private Integer userId;
    private String name;
    private LocalDateTime createdAt;
    private int roleId;

    public UserDto(Integer userId, String name, LocalDateTime createdAt, int roleId) {
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
        this.roleId = roleId;
    }
}
