package com.MyCityVoice.proj.controller;

import com.MyCityVoice.proj.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.MyCityVoice.proj.service.CityService;

import java.util.List;

@RestController
@RequestMapping ("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAll(){
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getOne (@PathVariable Long id){
        return cityService.getCity(id);
    }

    @PostMapping
    public City create(@RequestBody City City){
        return cityService.createCity(City);
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody City City) {
        City.setId(id);
        return cityService.updateCity(City);
    }

    @DeleteMapping("/{id}")
    public City delete(@PathVariable Long id){
        return cityService.deleteCity(id);
    }

}
