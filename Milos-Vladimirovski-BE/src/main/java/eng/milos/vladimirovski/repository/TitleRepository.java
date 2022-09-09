package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {
}
