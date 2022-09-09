package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.ProfessorDto;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import eng.milos.vladimirovski.mapper.ProfessorMapper;
import eng.milos.vladimirovski.repository.ProfessorRepository;
import eng.milos.vladimirovski.repository.SubjectRepository;
import eng.milos.vladimirovski.service.ProfessorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    private final SubjectRepository subjectRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ProfessorDto> findAllProfessors() {
        List<ProfessorEntity> professors = professorRepository.findAll();
        return professors.stream().map(professorMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public ProfessorDto add(ProfessorDto professor) throws EntityExistsException {

        return professorMapper.toDtoNoId(professorRepository.save(professorMapper.toEntityNoId(professor)));



    }

    @Override
    public Optional<ProfessorDto> findById(Long id) throws EntityExistsException {

        Optional<ProfessorEntity> studentEntity = professorRepository.findById(id);
        return studentEntity.map(professorMapper::toDto);
    }

    @Override
    public void delete(Long id) throws EntityExistsException {
        Optional<ProfessorEntity> studentEntity = professorRepository.findById(id);
        if (studentEntity.isEmpty()) {
            throw new EntityExistsException("Invalid student ID");
        }
        professorRepository.delete(studentEntity.get());

    }

    @Override
    public ProfessorDto edit(ProfessorDto professor) throws EntityExistsException {

        if (professorRepository.existsById(professor.getId())) {
            return professorMapper.toDto(professorRepository.save(professorMapper.toEntity(professor)));
        }
        throw new EntityExistsException("This professor does not exist");
    }

    @Override
    public Page<ProfessorDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
        Page<ProfessorDto> professors = professorRepository.findAll(pageable).map(professorMapper::toDto);
        return professors;

    }




}
