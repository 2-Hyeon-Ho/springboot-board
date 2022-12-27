package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Transactional
@Service
public class DefaultUserLoginService implements UserLoginService {

    private final UserService userService;

    public DefaultUserLoginService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isSession(HttpSession session) {
        if (Objects.isNull(session)) {
            return false;
        }
        String name = (String) session.getAttribute("name");

        if (Objects.isNull(name)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isMatches(HttpSession session, HttpServletResponse response, String name, String pwd) {
        if (userService.matches(name, pwd)) {
            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);

            int roleId = userService.getUserRoleId(name);
            User user = userService.getUser(name);
            session.setAttribute("user", user);
            session.setAttribute("roleId", roleId);
            return true;
        }
        return false;
    }
}
