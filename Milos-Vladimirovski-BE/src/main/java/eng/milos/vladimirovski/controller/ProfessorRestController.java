package eng.milos.vladimirovski.controller;


import eng.milos.vladimirovski.dto.ProfessorDto;

import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import eng.milos.vladimirovski.mapper.SubjectMapper;
import eng.milos.vladimirovski.service.ProfessorService;
import eng.milos.vladimirovski.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static eng.milos.vladimirovski.controller.ExaminationPeriodRestController.getStringStringMap;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("professors")
public class ProfessorRestController {

    private final ProfessorService professorService;
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    public ProfessorRestController(ProfessorService professorService, SubjectService subjectService, SubjectMapper subjectMapper) {
        this.professorService = professorService;
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping
    private List<ProfessorDto> findAll() {
        return professorService.findAllProfessors();
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> addProfessor(@Valid @RequestBody ProfessorDto professorDto) {
        try {
            ProfessorDto entity = professorService.add(professorDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws EntityExistsException {
        Optional<ProfessorDto> professor = professorService.findById(id);
        return professor.<ResponseEntity<Object>>map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid professor ID!"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<ProfessorDto> deletedProfessor = professorService.findById(id);
        try {
            professorService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("You delete professor with ID: " + id
                    + "And name " + deletedProfessor.get().getFirstName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Object> edit(@Valid @RequestBody ProfessorDto professorDto) {

        try {
            ProfessorDto entity = professorService.edit(professorDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }

    @GetMapping("filter")
    public ResponseEntity<Page<ProfessorDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String sortOrder) {
        return new ResponseEntity<Page<ProfessorDto>>
                (professorService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }







}
