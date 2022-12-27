package com.nhnacademy.springboot.board.repository.role;

import com.nhnacademy.springboot.board.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
