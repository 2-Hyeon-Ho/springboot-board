package com.nhnacademy.springboot.board.controller.post;

import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.nhnacademy.springboot.board.controller.LoginController.ADMIN;

@Controller
@RequestMapping("/post/delete/{postId}")
public class PostDeleteController {
    private final PostService postService;

    public PostDeleteController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String postDelete(@PathVariable("postId") int postId,
                             HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int roleId = (int) session.getAttribute("roleId");
        if(roleId == ADMIN) {
            postService.adminDeletePost(postId);
            return "redirect:/posts";
        }
        postService.deletePost(user.getUserId(), postId);
        return "redirect:/posts";
    }
}
