package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.enity.CityEntity;
import eng.milos.vladimirovski.enity.TitleEntity;

import java.util.List;
import java.util.Optional;

public interface TitleService {

    List<TitleEntity> getAllTitles();

    Optional<TitleEntity> getTitleById(Long id);

}
