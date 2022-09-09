package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.SubjectEntity;
import eng.milos.vladimirovski.mapper.SubjectMapper;
import eng.milos.vladimirovski.repository.SubjectRepository;
import eng.milos.vladimirovski.service.SubjectService;
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
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }


    @Override
    public List<SubjectDto> findAllSubjects() {
        List<SubjectEntity> subjects = subjectRepository.findAll();
        return subjects.stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SubjectDto add(SubjectDto subjectDto) throws EntityExistsException {
        return subjectMapper.toDtoNoId(subjectRepository.save(subjectMapper.toEntityNoId(subjectDto)));
    }

    @Override
    public Optional<SubjectDto> findById(Long id) throws Exception {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);

        return subjectEntity.map(subjectMapper::toDto);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
        if(subjectEntity.isEmpty()){
            throw new IllegalArgumentException("Invalid subject");
        }
        subjectRepository.delete(subjectEntity.get());

    }

    @Override
    public SubjectDto edit(SubjectDto subjectDto) throws Exception {
        if(subjectRepository.existsById(subjectDto.getId())){
            return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(subjectDto)));
        }
        throw  new IllegalArgumentException("This subject does not exist");
    }

    @Override
    public Page<SubjectDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
        Page<SubjectDto> subjects = subjectRepository.findAll(pageable).map(subjectMapper::toDto);
        return subjects;
    }
}
