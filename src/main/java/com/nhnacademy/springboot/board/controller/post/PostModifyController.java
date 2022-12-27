package com.nhnacademy.springboot.board.controller.post;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.domain.UserDto;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.request.PostRequest;
import com.nhnacademy.springboot.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import static com.nhnacademy.springboot.board.controller.LoginController.ADMIN;

@Controller
@RequestMapping("/posts/{postId}/modify")
public class PostModifyController {
    private final PostService postService;

    public PostModifyController(PostService postService) {
        this.postService = postService;
    }


    @ModelAttribute("post")
    public PostDto getPost(@PathVariable("postId") int postId) {
        return postService.getPost(postId);
    }
//
//    @GetMapping("/{postId}")
//    public String viewPost(@ModelAttribute("post") PostDto post,
//                           Model model) {
//        model.addAttribute("post", post);
//        return "postView";
//    }

    @GetMapping
    public String postModifyForm(@ModelAttribute("post") PostDto post,
                                 Model model) {
        model.addAttribute("post", post);
        return "postModify";
    }

    @PostMapping
    public String postModify(@PathVariable("postId") int postId,
                             HttpServletRequest request,
                             @ModelAttribute PostRequest postRequest) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int roleId = (int) session.getAttribute("roleId");

        if (roleId == ADMIN) {
            postService.adminModifyPost(user, postRequest.getTitle(), postRequest.getContent(), postId);
            return "redirect:/posts";
        }
        postService.modifyPost(user, postRequest.getTitle(), postRequest.getContent(), postId);
        return "redirect:/posts";
    }
}
