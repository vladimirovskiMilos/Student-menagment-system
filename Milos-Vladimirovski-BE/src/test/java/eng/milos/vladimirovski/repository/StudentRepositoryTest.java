package eng.milos.vladimirovski.repository;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepositoryTest {

    private StudentRepository studentRepository;



 /*   @Test
    public void findByIdTest(){

        Optional<StudentEntity> students = this.studentRepository.findById(1L);
        Assertions.assertNotNull(students);
        Assertions.assertFalse(students.isEmpty());
        Assertions.assertTrue(students.get().getId() == 1L);
        Assertions.assertEquals(students, students.get());

    }*/
}