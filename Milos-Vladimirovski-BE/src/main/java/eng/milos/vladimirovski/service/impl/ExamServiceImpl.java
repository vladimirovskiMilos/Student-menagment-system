package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ProfessorDto;
import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.ExamEntity;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import eng.milos.vladimirovski.mapper.ExamMapper;
import eng.milos.vladimirovski.mapper.SubjectMapper;
import eng.milos.vladimirovski.repository.ExamRepository;
import eng.milos.vladimirovski.repository.ProfessorRepository;
import eng.milos.vladimirovski.repository.SubjectRepository;
import eng.milos.vladimirovski.service.ExamService;
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
public class ExamServiceImpl implements ExamService {

    private final ExamRepository repository;
    private final ExamMapper mapper;


    private final ProfessorRepository professorRepository;


    private final SubjectRepository subjectRepository;

    public ExamServiceImpl(ExamRepository repository, ExamMapper mapper, ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ExamDto> findAll() {
        List<ExamEntity> exams = repository.findAll();
        return exams.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ExamDto add(ExamDto exam) throws EntityExistsException {
        return mapper.toDtoNoId(repository.save(mapper.toEntityNoId(exam)));
    }

    @Override
    public Optional<ExamDto> findById(Long id) throws EntityExistsException {
        Optional<ExamEntity> entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ExamEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Invalid exam ID");
        }
        repository.delete(entity.get());
    }

    @Override
    public ExamDto edit(ExamDto exam) throws Exception {
        if (repository.existsById(exam.getId())) {
            return mapper.toDto(repository.save(mapper.toEntity(exam)));
        }
        throw new Exception("This exam does not exist");
    }




      /*  List<StudentDto> entites = studentRepository.findAll(pageable).
        return entites;*/








    @Override
    public Page<ExamDto> findAllPageable(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
        Page<ExamDto> exams = repository.findAll(pageable).map(mapper::toDto);
        return exams;
    }
}
