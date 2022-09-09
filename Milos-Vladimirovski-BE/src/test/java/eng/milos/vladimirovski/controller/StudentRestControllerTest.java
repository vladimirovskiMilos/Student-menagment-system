package eng.milos.vladimirovski.controller;

import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.enity.CityEntity;
import eng.milos.vladimirovski.enity.StudentEntity;
import eng.milos.vladimirovski.repository.StudentRepository;
import eng.milos.vladimirovski.service.StudentService;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AllArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest

class StudentRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private final StudentService studentService;

 /*   @Test
    void getAll() {
        List<StudentEntity> students = studentRepository.findAll();
        Assertions.assertThat(students.size()).isGreaterThan(0);
    }*/
/*
    @Test
    void findById() {
        Optional<StudentDto> student = studentService.findById(1L);
      Assertions.assertThat(student);
    }*/

/*    @Test
    void addStudent() {

        StudentDto student = new StudentDto("test","test", 1L,  "2002", "test",  new CityEntity(17000L, "test")
       , 4L, "test");
        studentService.addStudent(student);
               //Long id, String email, String indexNumber, Long indexYear, String firstName,
       // String lastName, CityEntity city, Long currentYearOfStudy, String address
  }*/


}