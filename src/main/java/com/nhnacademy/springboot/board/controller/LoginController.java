package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    public static final int ADMIN = 1;
    public static final int USER = 2;
    private final UserLoginService loginService;

    public LoginController(UserLoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (!loginService.isSession(session)) {
            return "loginForm";
        }
        int roleId = (int) session.getAttribute("roleId");
        User user = (User) session.getAttribute("user");
        String name = user.getName();
        model.addAttribute("name", name);

        if (roleId == ADMIN) {
            return "adminHome";
        } else if (roleId == USER) {
            return "userHome";
        }
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("name") String name,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {

        HttpSession session = request.getSession(true);

        if (loginService.isMatches(session, response, name, pwd)) {
            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);

            int roleId = (int) session.getAttribute("roleId");
            session.setAttribute("roleId", roleId);
            session.setAttribute("name", name);
            model.addAttribute("name", name);
        }
        return "redirect:/login";
    }
}
