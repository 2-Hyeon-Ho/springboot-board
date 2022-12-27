package com.nhnacademy.springboot.board.controller.post;

import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.request.PostRequest;
import com.nhnacademy.springboot.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts/register")
public class PostRegisterController {
    private final PostService postService;

    public PostRegisterController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String postRegisterForm() {
        return "postRegister";
    }

    @PostMapping
    public String postRegister(HttpServletRequest request,
                               @ModelAttribute PostRequest postRequest) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        postService.registerPost(user, postRequest.getTitle(), postRequest.getContent());

        return "redirect:/posts";
    }
}
