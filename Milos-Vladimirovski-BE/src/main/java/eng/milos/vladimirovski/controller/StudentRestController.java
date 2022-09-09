package eng.milos.vladimirovski.controller;


import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.service.StudentService;
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
@RequestMapping("students")
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.findAllStudents();
    }

    @GetMapping("filter")
    public ResponseEntity<Page<StudentDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String sortOrder) {
        return new ResponseEntity<Page<StudentDto>>
                (studentService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> addStudent(@Valid @RequestBody StudentDto studentDto) {
        try {
            StudentDto entity = studentService.addStudent(studentDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping()
    public @ResponseBody ResponseEntity<Object> editStudent(@Valid @RequestBody StudentDto studentDto) {
        try {
            StudentDto entity = studentService.editStudent(studentDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws EntityExistsException {
        Optional<StudentDto> studentDto = studentService.findById(id);
        return studentDto.<ResponseEntity<Object>>map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student ID!"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }

    @GetMapping("search")
    public List<StudentDto> findStudentsByName(@RequestParam String name) {
        return studentService.findStudentsByFirstName(name);
    }

     Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
