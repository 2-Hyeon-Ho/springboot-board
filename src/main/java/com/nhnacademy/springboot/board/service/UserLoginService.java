package com.nhnacademy.springboot.board.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserLoginService {
    boolean isSession(HttpSession session);
    boolean isMatches(HttpSession session, HttpServletResponse response, String id, String pwd);
}
