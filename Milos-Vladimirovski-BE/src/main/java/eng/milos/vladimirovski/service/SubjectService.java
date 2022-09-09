package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.SubjectDto;
import org.springframework.data.domain.Page;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<SubjectDto> findAllSubjects();
    SubjectDto add (SubjectDto subjectDto) throws EntityExistsException;
    Optional<SubjectDto> findById(Long id) throws Exception;
    void delete (Long id) throws Exception;
    SubjectDto edit(SubjectDto subjectDto) throws Exception;
    Page<SubjectDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

}
