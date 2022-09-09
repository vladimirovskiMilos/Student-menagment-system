package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.enity.ExamEntity;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {




}
