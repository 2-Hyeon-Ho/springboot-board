package com.nhnacademy.springboot.board.repository.user;

import com.nhnacademy.springboot.board.entity.Role;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.repository.role.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    User user;
    Role role;

    @BeforeEach
    void setUp() {
        role = new Role(1, "admin");
        roleRepository.save(role);
        user = new User(null, "lee", "123", LocalDateTime.now(), role);
        userRepository.save(user);

    }


    @Test
    void findUserByName() {
        //when
        User actual = userRepository.findUserByName(user.getName());

        //then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void findUsersBy() {
        //when
        List<User> actual = userRepository.findUsersBy();

        //then
        assertThat(actual.get(0)).isEqualTo(user);
    }


}