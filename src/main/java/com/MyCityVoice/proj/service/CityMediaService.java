package com.MyCityVoice.proj.service;

import com.MyCityVoice.proj.model.CityMedia;
import com.MyCityVoice.proj.repository.CityMediaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityMediaService {
    private final CityMediaRepository repository;

    public List<CityMedia> getAll() {
        return repository.findAll();
    }

    public CityMedia getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public CityMedia create(CityMedia media) {
        return repository.save(media);
    }

    public CityMedia update(Long id, CityMedia media) {
        CityMedia existing = getById(id);

        existing.setCityName(media.getCityName());
        existing.setMediaUrl(media.getMediaUrl());
        existing.setMediaType(media.getMediaType());
        existing.setDescription(media.getDescription());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<CityMedia> getByCity(String city) {
        return repository.findByCityName(city);
    }
}
