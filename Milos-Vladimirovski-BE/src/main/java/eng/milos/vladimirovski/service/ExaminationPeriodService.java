package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ExaminationPeriodDto;
import eng.milos.vladimirovski.enity.ExamEntity;
import org.springframework.data.domain.Page;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface ExaminationPeriodService {

    List<ExaminationPeriodDto> findAll();

    ExaminationPeriodDto add(ExaminationPeriodDto exam) throws EntityExistsException;

    Optional<ExaminationPeriodDto> findById(Long id) throws EntityExistsException;


    void delete(Long id) throws Exception;


    ExaminationPeriodDto edit(ExaminationPeriodDto exam) throws Exception;

    Page<ExaminationPeriodDto> findAllPageable(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);



}
