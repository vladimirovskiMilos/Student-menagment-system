package eng.milos.vladimirovski.service.impl;


import eng.milos.vladimirovski.enity.CityEntity;
import eng.milos.vladimirovski.enity.TitleEntity;
import eng.milos.vladimirovski.repository.CityRepository;
import eng.milos.vladimirovski.repository.TitleRepository;
import eng.milos.vladimirovski.service.TitleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleServiceImpl implements TitleService {


    private final TitleRepository titleRepository;

    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }


    @Override
    public List<TitleEntity> getAllTitles() {
        return titleRepository.findAll();
    }

    @Override
    public Optional<TitleEntity> getTitleById(Long id) {
        Optional<TitleEntity> title = titleRepository.findById(id);
        if(title.isPresent()){
            return Optional.of(title.get());
        }
        return Optional.empty();
    }

}
