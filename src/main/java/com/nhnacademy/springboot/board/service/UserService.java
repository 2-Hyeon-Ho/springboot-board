package com.nhnacademy.springboot.board.service;


import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;

import java.util.List;

public interface UserService {
     User getUser(String name);
//     User getUserDto(String name);
     int getUserRoleId(String name);

     boolean matches(String id, String password);

     boolean isExist(String id);

     List<User> findAll();
}
