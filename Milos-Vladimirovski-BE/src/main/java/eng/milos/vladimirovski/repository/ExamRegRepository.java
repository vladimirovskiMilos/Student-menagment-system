package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.ExamRegEntity;
import eng.milos.vladimirovski.enity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRegRepository extends JpaRepository<ExamRegEntity, Long> {

    @Query(value = "select * from student where first_name LIKE %:name%  OR last_name LIKE %:name%",
            nativeQuery = true)
    List<StudentEntity> findStudentsByName(@Param("name") String name);
}
