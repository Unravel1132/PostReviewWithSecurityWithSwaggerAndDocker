package com.PostBloging2.PostBloging.Controllers;

import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewPage {

    @Autowired
    private ReviewServiceImpl reviewService;

    @GetMapping("/{postId}")
    public String showReviewForm(@PathVariable Long postId, Model model) {
        ReviewEntity postEntity = reviewService.getReviewById(postId);
         model.addAttribute("postId", postEntity);
        return "posts";
    }
}