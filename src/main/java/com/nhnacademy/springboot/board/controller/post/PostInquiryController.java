package com.nhnacademy.springboot.board.controller.post;

import com.nhnacademy.springboot.board.domain.PostDto;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/posts")
public class PostInquiryController {
    private final PostService postService;

    public PostInquiryController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPosts(Model model, Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), 3);
        Page<Post> posts = postService.getPosts(page);
        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/delete/list")
    public String getDeletePosts(Model model, Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), 3);
        Page<Post> deletePosts = postService.getDeletePosts(page);
        model.addAttribute("deletePosts", deletePosts);

        return "deletePostList";
    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable("postId") int postId, Model model) {
        PostDto post = postService.getPost(postId);

        modifierCheck(model, post);
        model.addAttribute("post", post);

        return "postView";
    }

    @GetMapping("/delete/{postId}")
    public String getDeletePost(@PathVariable("postId") int postId, Model model) {
        PostDto post = postService.getPost(postId);

        modifierCheck(model, post);
        model.addAttribute("deletePost", post);

        return "deletePostView";
    }

    private void modifierCheck(Model model, PostDto post) {
        if(Objects.nonNull(post.getModifierName())) {
            model.addAttribute("modifier", post.getModifierName());
            model.addAttribute("modifiedAt", post.getModifiedAt());
        }
    }
}
