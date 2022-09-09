package eng.milos.vladimirovski.service;

import eng.milos.vladimirovski.dto.PassExamDto;
import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;


public interface PassExamService {

    List<PassExamDto> getAll();

    Optional<PassExamDto> findById(Long id);

    PassExamDto add(PassExamDto entity) throws EntityExistsException;

    PassExamDto edit(PassExamDto passExamDto) throws Exception;

}
