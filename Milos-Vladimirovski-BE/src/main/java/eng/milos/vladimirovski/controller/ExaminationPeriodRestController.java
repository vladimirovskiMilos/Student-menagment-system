package eng.milos.vladimirovski.controller;


import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ExaminationPeriodDto;
import eng.milos.vladimirovski.dto.ProfessorDto;
import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.ExaminationPeriodEntity;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import eng.milos.vladimirovski.mapper.ExamMapper;
import eng.milos.vladimirovski.mapper.ExaminationPeriodMapper;
import eng.milos.vladimirovski.mapper.ProfessorMapper;
import eng.milos.vladimirovski.mapper.SubjectMapper;
import eng.milos.vladimirovski.service.ExamService;
import eng.milos.vladimirovski.service.ExaminationPeriodService;
import eng.milos.vladimirovski.service.ProfessorService;
import eng.milos.vladimirovski.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("periods")
@AllArgsConstructor
public class ExaminationPeriodRestController {

    private final ExaminationPeriodService service;
    private final ProfessorService professorService;
    private final SubjectService subjectService;
    private final ProfessorMapper professorMapper;
    private final SubjectMapper subjectMapper;
    private final ExaminationPeriodMapper mapper;
    private final ExamService examService;


    @GetMapping
    public List<ExaminationPeriodDto> findAll() {
        return service.findAll();
    }
    @GetMapping("filter")
    public ResponseEntity<Page<ExaminationPeriodDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                                 @RequestParam(defaultValue = "name") String sortBy,
                                                 @RequestParam(defaultValue = "asc") String sortOrder) {
        return new ResponseEntity<Page<ExaminationPeriodDto>>
                (service.findAllPageable(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }


    @PostMapping()
    public @ResponseBody ResponseEntity<Object> add(@Valid @RequestBody ExaminationPeriodDto exam) {
        try {

            ExaminationPeriodDto entity = service.add(exam);
            System.out.println("entity-----------------------------------" + entity);

           // ExaminationPeriodDto e = mapper.toDtoNoId(repository.save(mapper.toEntityNoId(exam)));
            List<ProfessorDto> professorList = professorService.findAllProfessors();

            for(SubjectDto eee : subjectService.findAllSubjects()){

                ExamDto newExam = new ExamDto(mapper.toEntity(entity), subjectMapper.toEntity(eee), professorMapper.toEntity(professorList.get(0)), entity.getStart());
                examService.add(newExam);
                System.out.println("newExam-------------------------------------------" + newExam.getSubject().getName()+ newExam.getExaminationPeriod().getName());
            }
            System.out.println("entity-----------------------------------" + entity);
            return ResponseEntity.status(HttpStatus.OK).body(entity);

        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping()
    public @ResponseBody ResponseEntity<Object> edit(@Valid @RequestBody ExaminationPeriodDto exam) {
        try {

            ExaminationPeriodDto entity = service.edit(exam);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws EntityExistsException {
        Optional<ExaminationPeriodDto> examPeriod = service.findById(id);
        return examPeriod.<ResponseEntity<Object>>map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid exam period ID!"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        try {

            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted exam period with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }

    static Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
