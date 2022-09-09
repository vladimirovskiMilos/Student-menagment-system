package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.PassExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassExamRepository extends JpaRepository<PassExamEntity, Long> {


}
