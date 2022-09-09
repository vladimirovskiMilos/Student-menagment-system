package eng.milos.vladimirovski.repository;

import eng.milos.vladimirovski.enity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query(value = "select * from student where first_name LIKE %:name%  OR last_name LIKE %:name%",
            nativeQuery = true)
    List<StudentEntity> findStudentsByName(@Param("name") String name);

}
