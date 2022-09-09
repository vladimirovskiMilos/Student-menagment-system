package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ProfessorDto;
import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import org.springframework.data.domain.Page;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface ExamService {

    List<ExamDto> findAll();

    ExamDto add(ExamDto exam) throws EntityExistsException;

    Optional<ExamDto> findById(Long id) throws EntityExistsException;


    void delete(Long id) throws Exception;


    ExamDto edit(ExamDto exam) throws Exception;



    Page<ExamDto> findAllPageable(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);


}
