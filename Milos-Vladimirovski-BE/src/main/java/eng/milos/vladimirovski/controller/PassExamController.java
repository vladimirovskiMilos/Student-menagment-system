package eng.milos.vladimirovski.controller;

import eng.milos.vladimirovski.dto.PassExamDto;
import eng.milos.vladimirovski.exception.DuplicateEntityException;
import eng.milos.vladimirovski.exception.InvalidEntityId;
import eng.milos.vladimirovski.service.PassExamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("passed-exams")
@AllArgsConstructor
public class PassExamController {

    private final PassExamService  passExamService;

    @GetMapping
    public List<PassExamDto> getAll(){
        return passExamService.getAll();
    }
    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws InvalidEntityId{
        Optional<PassExamDto> passExam = passExamService.findById(id);
        return passExam.<ResponseEntity<Object>>map(passExamDto ->
                ResponseEntity.status(HttpStatus.OK).body(passExamDto)).orElseGet(()
        -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passed exam width id " + id + " does not exists" ));
    }
    @PostMapping
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody PassExamDto passExam){
        try{
            PassExamDto entity = passExamService.add(passExam);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public @ResponseBody ResponseEntity<Object> edit(@Valid @RequestBody PassExamDto passExam){
        try {
            PassExamDto entity = passExamService.edit(passExam);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
