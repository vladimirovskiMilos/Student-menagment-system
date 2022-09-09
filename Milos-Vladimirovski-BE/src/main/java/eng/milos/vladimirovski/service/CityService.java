package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.enity.CityEntity;

import java.util.List;
import java.util.Optional;


public interface CityService {

    List<CityEntity> getAllCities();
    Optional<CityEntity> getCityById(Long zipCode);
}
