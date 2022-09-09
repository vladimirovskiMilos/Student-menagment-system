package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {


}
