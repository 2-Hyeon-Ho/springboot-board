package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.exception.UserNotFoundException;
import com.nhnacademy.springboot.board.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String name) {
        if (Objects.isNull(userRepository.findUserByName(name))) {
            throw new UserNotFoundException();
        }
        return userRepository.findUserByName(name);
    }

//    @Override
//    public User getUserDto(String name) {
//        if(Objects.isNull(userRepository.findUserByName(name))) {
//            throw new UserNotFoundException();
//        }
//        return userRepository.findUserByName(name);
//    }

    @Override
    public int getUserRoleId(String name) {
        User user = userRepository.findUserByName(name);
        return user.getRole().getRoleId();
    }

    @Override
    public boolean matches(String name, String password) {
        return Optional.of(getUser(name))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public boolean isExist(String name) {
        return findAll().stream().anyMatch(it -> it.getName().equals(name));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findUsersBy();
    }
}
