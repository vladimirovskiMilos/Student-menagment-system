package eng.milos.vladimirovski.controller;


import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.service.ExamService;
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
@RequestMapping("exams")
public class ExamRestController {

    private final ExamService service;

    public ExamRestController(ExamService service) {
        this.service = service;
    }


    @GetMapping
    public List<ExamDto> findAll() {
        return service.findAll();
    }


    @GetMapping("filter")
    public ResponseEntity<Page<ExamDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String sortOrder) {
        return new ResponseEntity<Page<ExamDto>>
                (service.findAllPageable(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }


    @PostMapping()
    public @ResponseBody ResponseEntity<Object> add(@Valid @RequestBody ExamDto exam) {
        try {
            ExamDto entity = service.add(exam);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping()
    public @ResponseBody ResponseEntity<Object> editStudent(@Valid @RequestBody ExamDto exam) {
        try {

            ExamDto entity = service.edit(exam);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws EntityExistsException {
        Optional<ExamDto> exam = service.findById(id);
        return exam.<ResponseEntity<Object>>map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid exam ID!"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        try {

            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted exam with ID: " + id);
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
