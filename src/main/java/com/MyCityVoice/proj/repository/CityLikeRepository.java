package com.MyCityVoice.proj.repository;

import com.MyCityVoice.proj.model.City;
import com.MyCityVoice.proj.model.CityLike;
import com.MyCityVoice.proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityLikeRepository extends JpaRepository<CityLike, Long> {

    boolean existsByUserAndCity(User user, City city);

    Optional<CityLike> findByUserAndCity(User user, City city);

    long countByCity(City city);
}
