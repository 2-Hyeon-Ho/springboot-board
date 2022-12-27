package com.nhnacademy.springboot.board.controller.post;

import com.nhnacademy.springboot.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts/{postId}/restore")
public class PostRestoreController {
    private final PostService postService;

    public PostRestoreController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String restorePost(@PathVariable("postId") int postId) {
        postService.restorePost(postId);

        return "redirect:/posts/delete/list";
    }
}
