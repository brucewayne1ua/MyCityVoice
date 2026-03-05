package com.MyCityVoice.proj.repository;

import com.MyCityVoice.proj.model.CityMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CityMediaRepository extends JpaRepository <CityMedia, Long>{
    List<CityMedia> findByCityName(String cityName);
}
