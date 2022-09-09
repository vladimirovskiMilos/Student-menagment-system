package eng.milos.vladimirovski.controller;
import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.service.SubjectService;
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

import static eng.milos.vladimirovski.controller.ExaminationPeriodRestController.getStringStringMap;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "subjects")
public class SubjectRestController {

    private final SubjectService subjectService;

    public SubjectRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @GetMapping
    public List<SubjectDto> getAll(){
        return subjectService.findAllSubjects();
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> addSubject(@Valid @RequestBody SubjectDto subjectDto) {
        try {
            SubjectDto entity = subjectService.add(subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PutMapping()
    public @ResponseBody ResponseEntity<Object> editSubject(@Valid @RequestBody SubjectDto subjectDto) {
        try {

            SubjectDto entity = subjectService.edit(subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
        Optional<SubjectDto> subjectDto = subjectService.findById(id);
        return subjectDto.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("MY MESSAGE ID!"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        try {

            subjectService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("filter")
    public ResponseEntity<Page<SubjectDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String sortOrder) {
        return new ResponseEntity<Page<SubjectDto>>
                (subjectService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }



}
