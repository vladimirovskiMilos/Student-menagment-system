package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.enity.CityEntity;
import eng.milos.vladimirovski.repository.CityRepository;
import eng.milos.vladimirovski.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {


    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityEntity> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<CityEntity> getCityById(Long zipCode) {
        Optional<CityEntity> city = cityRepository.findById(zipCode);
        if(city.isPresent()){
            return Optional.of(city.get());
        }
        return Optional.empty();
    }
}
