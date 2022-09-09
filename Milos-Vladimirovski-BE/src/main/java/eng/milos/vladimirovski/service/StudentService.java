package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.StudentDto;
import org.springframework.data.domain.Page;
import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> findAllStudents();

    StudentDto addStudent(StudentDto student) throws EntityExistsException;

    Optional<StudentDto> findById(Long id) throws EntityExistsException;


    void deleteStudent(Long id) throws Exception;


    StudentDto editStudent(StudentDto studentDto) throws Exception;

    Page<StudentDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    List<StudentDto> findStudentsByFirstName(String name);


}
