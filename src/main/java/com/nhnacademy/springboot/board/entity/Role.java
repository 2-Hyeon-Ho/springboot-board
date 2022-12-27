package com.nhnacademy.springboot.board.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "Userdivision")
public class Role {
    @Id
    @Column(name = "userdivision_id")
    private int roleId;

    @Column(name = "role")
    private String role;
}
