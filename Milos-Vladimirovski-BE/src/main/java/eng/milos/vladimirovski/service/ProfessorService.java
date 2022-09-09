package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.ProfessorDto;
import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import org.springframework.data.domain.Page;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface ProfessorService {



    List<ProfessorDto> findAllProfessors();

    ProfessorDto add(ProfessorDto professor) throws EntityExistsException;

    Optional<ProfessorDto> findById(Long id) throws EntityExistsException;

    void delete(Long id) throws EntityExistsException;

    ProfessorDto edit(ProfessorDto professor) throws EntityExistsException;

    Page<ProfessorDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);


}
