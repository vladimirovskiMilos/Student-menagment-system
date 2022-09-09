package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ExamRegDto;
import eng.milos.vladimirovski.exception.YearOfStudyException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface ExamRegService {

    List<ExamRegDto> findAll();

    ExamRegDto add(ExamRegDto examReg) throws EntityExistsException, YearOfStudyException;

    Optional<ExamRegDto> findById(Long id) throws EntityExistsException;


    void delete(Long id) throws Exception;


    ExamRegDto edit(ExamRegDto examReg) throws Exception;
}
