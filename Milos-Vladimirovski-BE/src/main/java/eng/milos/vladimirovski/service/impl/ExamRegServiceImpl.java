package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.ExamRegDto;
import eng.milos.vladimirovski.enity.ExamEntity;
import eng.milos.vladimirovski.enity.ExamRegEntity;
import eng.milos.vladimirovski.enity.StudentEntity;
import eng.milos.vladimirovski.exception.YearOfStudyException;
import eng.milos.vladimirovski.mapper.ExamRegMapper;
import eng.milos.vladimirovski.repository.ExamRegRepository;
import eng.milos.vladimirovski.repository.ExamRepository;
import eng.milos.vladimirovski.repository.StudentRepository;
import eng.milos.vladimirovski.service.ExamRegService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidTimeoutException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamRegServiceImpl implements ExamRegService {

    private final ExamRegRepository repository;
    private final ExamRegMapper mapper;

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;


    @Override
    public List<ExamRegDto> findAll() {
        List<ExamRegEntity> examsReg = repository.findAll();
        return examsReg.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ExamRegDto add(ExamRegDto examReg) throws EntityExistsException, YearOfStudyException {


        Optional<StudentEntity> studentToApply = studentRepository.findById(examReg.getStudent().getId());
        Optional<ExamEntity>  applyedExam = examRepository.findById(examReg.getExam().getId());
        List<ExamRegEntity> registeredExams = repository.findAll();
        for(ExamRegEntity e : registeredExams){
            if(Objects.equals(e.getStudent().getId(), studentToApply.get().getId()) &&
                    Objects.equals(e.getExam().getId(), applyedExam.get().getId())){
                throw new EntityExistsException("You cannot register two times for same exam");
            }else if(studentToApply.get().getCurrentYearOfStudy() != applyedExam.get().getSubject().getYearOfStudy()){
                throw new YearOfStudyException("Error \n" +
                        "Subject year of study is: ", e.getExam().getSubject().getYearOfStudy() +"\n" +
                        "Student current year is:  " + e.getStudent().getCurrentYearOfStudy() + "\n" +
                        "Student year of study and subject year must be seme.");
            }
        }

        return mapper.toDtoNoId(repository.save(mapper.toEntityNoId(examReg)));
    }

    @Override
    public Optional<ExamRegDto> findById(Long id) throws EntityExistsException {
        Optional<ExamRegEntity> entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ExamRegEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Invalid exam registration ID");
        }
        repository.delete(entity.get());

    }

    @Override
    public ExamRegDto edit(ExamRegDto examReg) throws Exception {
        if (repository.existsById(examReg.getId())) {
            return mapper.toDto(repository.save(mapper.toEntity(examReg)));
        }
        throw new Exception("This exam registration does not exist");
    }
}
