package com.MyCityVoice.proj.repository;

import com.MyCityVoice.proj.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByCityIdAndApproved(
            Long cityId,
            Boolean approved,
            Pageable pageable
    );

    Optional<Review> findByUserIdAndCityId(Long userId, Long cityId);

    Double getAverageRatingByCityId(Long cityId);
}