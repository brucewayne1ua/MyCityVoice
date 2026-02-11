package com.MyCityVoice.proj.service;

import com.MyCityVoice.proj.model.City;
import com.MyCityVoice.proj.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository){
        this.cityRepository = cityRepository;}

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCity(String name) {
        return cityRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("City not found: " + name));
    }

    public City getCity(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
    }


    public City createCity(City city){
        return cityRepository.save(city);
    }

    public City updateCity(City city){
        return cityRepository.save(city);
    }

    public City deleteCity(Long id){
        cityRepository.deleteById(id);
        return null;
    }


}
