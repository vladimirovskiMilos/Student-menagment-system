package eng.milos.vladimirovski.controller;


import eng.milos.vladimirovski.enity.CityEntity;
import eng.milos.vladimirovski.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cities")
public class CityRestController {

        private final CityService cityService;


    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityEntity> getAll(){
        return cityService.getAllCities();
    }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable (name = "id") Long id){
        Optional<CityEntity> city =  cityService.getCityById(id);
        if(city.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(city.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Citi zip is invalid");

    }




}
