package com.MyCityVoice.proj.controller;

import com.MyCityVoice.proj.model.User;
import com.MyCityVoice.proj.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MyCityVoice.proj.repository.ReviewRepository;
import com.MyCityVoice.proj.model.Review;
import com.MyCityVoice.proj.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getAll(){return reviewService.getAllRewievs();
}
    @GetMapping("/{id}/user")
    public User getReviewUser(@PathVariable Long id){
        Review review = reviewService.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        return review.getUser();
    }
}
