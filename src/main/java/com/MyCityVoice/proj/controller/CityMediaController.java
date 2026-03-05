package com.MyCityVoice.proj.controller;

import com.MyCityVoice.proj.model.CityMedia;
import com.MyCityVoice.proj.repository.CityMediaRepository;
import com.MyCityVoice.proj.service.CityMediaService;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city-media")
@RequiredArgsConstructor
public class CityMediaController {
    private final CityMediaService service;

    @GetMapping
    public List<CityMedia> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CityMedia getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/city/{name}")
    public List<CityMedia> getByCity(@PathVariable String name) {
        return service.getByCity(name);
    }

    @PostMapping
    public CityMedia create(@RequestBody CityMedia media) {
        return service.create(media);
    }

    @PutMapping("/{id}")
    public CityMedia update(
            @PathVariable Long id,
            @RequestBody CityMedia media) {
        return service.update(id, media);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
