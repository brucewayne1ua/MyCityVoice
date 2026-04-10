package com.MyCityVoice.proj.service;

import com.MyCityVoice.proj.model.City;
import com.MyCityVoice.proj.model.CityLike;
import com.MyCityVoice.proj.model.User;
import com.MyCityVoice.proj.repository.CityLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityLikeService {

    private final CityLikeRepository cityLikeRepository;

    public CityLikeService(CityLikeRepository cityLikeRepository) {
        this.cityLikeRepository = cityLikeRepository;
    }
    public void like(User user, City city) {

        boolean exists = cityLikeRepository.existsByUserAndCity(user, city);

        if (exists) return;

        CityLike like = new CityLike();
        like.setUser(user);
        like.setCity(city);

        cityLikeRepository.save(like);
    }
    public void unlike(User user, City city) {

        CityLike like = cityLikeRepository
                .findByUserAndCity(user, city)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        cityLikeRepository.delete(like);
    }
    public boolean toggle(User user, City city) {

        Optional<CityLike> like = cityLikeRepository.findByUserAndCity(user, city);

        if (like.isPresent()) {
            cityLikeRepository.delete(like.get());
            return false; // теперь НЕ лайкнуто
        } else {
            CityLike newLike = new CityLike();
            newLike.setUser(user);
            newLike.setCity(city);

            cityLikeRepository.save(newLike);
            return true; // теперь лайкнуто
        }
    }
    public long count(City city) {
        return cityLikeRepository.countByCity(city);
    }
}

