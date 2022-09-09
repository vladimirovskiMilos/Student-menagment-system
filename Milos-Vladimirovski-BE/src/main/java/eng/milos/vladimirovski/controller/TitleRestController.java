package eng.milos.vladimirovski.controller;

import eng.milos.vladimirovski.enity.TitleEntity;
import eng.milos.vladimirovski.service.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("titles")
public class TitleRestController {

    private final TitleService titleService;

    public TitleRestController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping
    public List<TitleEntity> getAll(){
        return titleService.getAllTitles();
    }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable(name = "id") Long id){
        Optional<TitleEntity> city =  titleService.getTitleById(id);
        if(city.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(city.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid Title ID");

    }
}
