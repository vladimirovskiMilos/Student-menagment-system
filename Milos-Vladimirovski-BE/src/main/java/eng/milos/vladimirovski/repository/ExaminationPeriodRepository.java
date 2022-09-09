package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.ExaminationPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationPeriodRepository  extends JpaRepository<ExaminationPeriodEntity, Long> {
}
