package com.nhnacademy.springboot.board.repository.user;

import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByName(String name);

//    UserDto findByName(String name);
    List<User> findUsersBy();
}
