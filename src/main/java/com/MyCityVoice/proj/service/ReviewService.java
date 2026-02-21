package com.MyCityVoice.proj.service;

import com.MyCityVoice.proj.model.City;
import com.MyCityVoice.proj.model.Review;
import com.MyCityVoice.proj.model.User;
import com.MyCityVoice.proj.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final CityService cityService;

    // Создание отзыва
    public Review createReview(Long userId, Long cityId, Integer rating, String content) {

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        reviewRepository.findByUserIdAndCityId(userId, cityId)
                .ifPresent(r -> {
                    throw new IllegalStateException("User already left review for this city");
                });

        User user = userService.getUser(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        City city = cityService.getCity(cityId);
        if (city == null) {
            throw new EntityNotFoundException("City not found");
        }

        Review review = Review.builder()
                .user(user)
                .city(city)
                .rating(rating)
                .content(content)
                .approved(false)
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> getAllRewievs(){

        return reviewRepository.findAll();
    }

    public Optional<Review> getById(Long id) {
        return reviewRepository.findById(id);
    }
}