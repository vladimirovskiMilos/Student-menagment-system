package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ExaminationPeriodDto;
import eng.milos.vladimirovski.enity.*;
import eng.milos.vladimirovski.mapper.ExamMapper;
import eng.milos.vladimirovski.mapper.ExaminationPeriodMapper;
import eng.milos.vladimirovski.repository.ExamRepository;
import eng.milos.vladimirovski.repository.ExaminationPeriodRepository;
import eng.milos.vladimirovski.repository.ProfessorRepository;
import eng.milos.vladimirovski.repository.SubjectRepository;
import eng.milos.vladimirovski.service.ExamService;
import eng.milos.vladimirovski.service.ExaminationPeriodService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExaminationPeriodServiceImpl implements ExaminationPeriodService {


    private final ExaminationPeriodRepository repository;
    private final ExaminationPeriodMapper mapper;
    private final ExamRepository examRepository;

    private final ExamService examService;
    private final ExamMapper examMapper;
    private final SubjectRepository subjectRepository;

    @Override
    public List<ExaminationPeriodDto> findAll() {
        List<ExaminationPeriodEntity> entity = repository.findAll();
        return entity.stream().map(mapper::toDto).collect(Collectors.toList());

    }

    @Override
    public ExaminationPeriodDto add(ExaminationPeriodDto exam) throws EntityExistsException {
        //proveri da li je neki od ispitnih rokova aktivan, ako nije sacuvaj, ako jeste proveri da li je aktivan novi i odbij ako jeste
        List<ExaminationPeriodEntity> allPeriods = repository.findAll();

        for (ExaminationPeriodEntity e : allPeriods) {
            if (e.isActive() && exam.isActive()) {
                throw new EntityExistsException("You cannot have 2 active exams in same time");
            }
            //proveri da li se preklapaju datumi u ispitnim rokovima ako ne sacuvaj u suprotnom odbij operaciju
            if (e.getStart().getTime() <= exam.getEnd().getTime() && exam.getStart().getTime() <= e.getEnd().getTime()) {
                throw new EntityExistsException("Dates cannot be overloaded");
            }
            if (exam.getStart().getTime() > exam.getEnd().getTime()) {
                throw new EntityExistsException("End date must be greater than the start date ");
            }
        }

             return mapper.toDto(repository.save(mapper.toEntityNoId(exam)));


    }

    @Override
    public Optional<ExaminationPeriodDto> findById(Long id) throws EntityExistsException {
        Optional<ExaminationPeriodEntity> entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ExaminationPeriodEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityExistsException("Invalid student ID");
        }
        repository.delete(entity.get());
    }

    @Override
    public ExaminationPeriodDto edit(ExaminationPeriodDto exam) throws Exception {
        List<ExaminationPeriodEntity> allPeriods = repository.findAll();
        for (ExaminationPeriodEntity e : allPeriods) {
            if (e.isActive() && exam.isActive()) {
                throw new EntityExistsException("You cannot have 2 active exams in same time");
            }

        }

        if (repository.existsById(exam.getId())) {
            return mapper.toDto(repository.save(mapper.toEntity(exam)));
        }
        throw new EntityExistsException("This ExaminationPeriod does not exist");
    }

    @Override
    public Page<ExaminationPeriodDto> findAllPageable(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
        Page<ExaminationPeriodDto> periods = repository.findAll(pageable).map(mapper::toDto);
        return periods;
    }


}
