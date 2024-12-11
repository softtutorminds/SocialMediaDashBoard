package com.example.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.socialmedia.model.Post;
import com.example.socialmedia.service.PostService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String viewAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "post_list";
    }

    @GetMapping("/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post_form";
    }

    @PostMapping
    public String savePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        post.ifPresent(p -> model.addAttribute("post", p));
        return "post_form";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }
}
