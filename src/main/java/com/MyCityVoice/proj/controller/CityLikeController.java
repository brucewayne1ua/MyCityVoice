package com.MyCityVoice.proj.controller;

import com.MyCityVoice.proj.model.City;
import com.MyCityVoice.proj.model.User;
import com.MyCityVoice.proj.service.CityLikeService;
import com.MyCityVoice.proj.service.CityService;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CityLikeController {

    private final CityLikeService cityLikeService;
    private final CityService cityService;

    public CityLikeController(CityLikeService cityLikeService,
                              CityService cityService) {
        this.cityLikeService = cityLikeService;
        this.cityService = cityService;
    }

    @PostMapping("/{cityId}/like")
    public void like(@PathVariable Long cityId,
                     @RequestBody User user) {

        City city = cityService.getById(cityId);
        cityLikeService.like(user, city);
    }

    @GetMapping("/{cityId}/like")
    public long showlike(@PathVariable Long cityId,
                         @RequestBody User user){
        City city = cityService.getById(cityId);
        return cityLikeService.count(city);
    }

    @DeleteMapping("/{cityId}/like")
    public void unlike(@PathVariable Long cityId,
                       @RequestBody User user) {

        City city = cityService.getById(cityId);
        cityLikeService.unlike(user, city);
    }

    @PostMapping("/{cityId}/like/toggle")
    public boolean toggle(@PathVariable Long cityId,
                          @RequestBody User user) {

        City city = cityService.getById(cityId);
        return cityLikeService.toggle(user, city);
    }

}