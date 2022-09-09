package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.CityEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    public CityRepositoryTest(){

    }
    @Test
    public void saveTest(){
        CityEntity city = new CityEntity(11111L, "TEST");
        CityEntity saved = (CityEntity)this.cityRepository.save(city);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(city, saved);
    }

}