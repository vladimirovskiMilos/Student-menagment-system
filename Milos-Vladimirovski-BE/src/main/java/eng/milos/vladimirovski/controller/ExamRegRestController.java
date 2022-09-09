package eng.milos.vladimirovski.controller;


import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.ExamRegDto;
import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.enity.ExamEntity;
import eng.milos.vladimirovski.exception.YearOfStudyException;
import eng.milos.vladimirovski.service.ExamRegService;
import eng.milos.vladimirovski.service.ExamService;
import eng.milos.vladimirovski.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static eng.milos.vladimirovski.controller.ExamRestController.getStringStringMap;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("ex-registrations")
@AllArgsConstructor
public class ExamRegRestController {

    private final ExamRegService service;
    private final StudentService studentService;

    private final ExamService examService;

    @GetMapping
    public List<ExamRegDto> findAll() {
        return service.findAll();
    }


    @PostMapping()
    public @ResponseBody ResponseEntity<Object> applyForExam(@Valid @RequestBody ExamRegDto examRegDto) {
        try {

            ExamRegDto entity = service.add(examRegDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);

        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (YearOfStudyException e) {
            throw new RuntimeException(e);
        }
    }

    /*Old post method with check everything for registration
    *  @PostMapping()
    public @ResponseBody ResponseEntity<Object> addStudent(@Valid @RequestBody ExamRegDto examRegDto) {
        try {
            for(ExamEntity e: examRegDto.getExams()){
                if(examRegDto.getStudent().getCurrentYearOfStudy() != e.getSubject().getYearOfStudy()){
                    return ResponseEntity.status(HttpStatus.OK).body("Student and the subject are not in the same year");
                }
            }

            if(examRegDto.getExamPeriod().isActive()){
                if(Date.from(LocalDate.now().plusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        .compareTo(examRegDto.getExamPeriod().getStart()) >0){
                    return ResponseEntity.status(HttpStatus.OK).body("You cannot register for the exam");
                }
            }



            ExamRegDto entity = service.add(examRegDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/

 /*   @PutMapping()
    public @ResponseBody ResponseEntity<Object> editStudent(@Valid @RequestBody StudentDto studentDto) {
        try {

            StudentDto entity = studentService.editStudent(studentDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/


    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws EntityExistsException {
        Optional<ExamRegDto> examRegDto = service.findById(id);
        return examRegDto.<ResponseEntity<Object>>map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid exam registration ID!"));
    }
    @GetMapping("/student/{id}")
    public @ResponseBody ResponseEntity<Object> findStudent(@PathVariable Long id) throws EntityExistsException {
        Optional<StudentDto> student = studentService.findById(id);
        return student.<ResponseEntity<Object>>map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student  ID!"));
    }


    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        try {

            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted exam registration with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }


}
