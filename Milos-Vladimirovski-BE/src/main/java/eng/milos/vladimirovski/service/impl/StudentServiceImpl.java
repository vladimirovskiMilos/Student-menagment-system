package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.enity.StudentEntity;
import eng.milos.vladimirovski.mapper.StudentMapper;
import eng.milos.vladimirovski.repository.StudentRepository;
import eng.milos.vladimirovski.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> findAllStudents() {

        List<StudentEntity> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto addStudent(StudentDto student) throws EntityExistsException {
        List<StudentEntity> students = studentRepository.findAll();

        for (StudentEntity s : students) {
            if (s.getIndexYear() == student.getIndexYear() && s.getIndexNumber().equals(student.getIndexNumber())) {
                throw new EntityExistsException("student cannot have same index number and index year");
            }


        }

        return studentMapper.toDtoNoId(studentRepository.save(studentMapper.toEntityNoId(student)));
    }

    @Override
    public StudentDto editStudent(StudentDto studentDto) throws Exception {
        if (studentRepository.existsById(studentDto.getId())) {
            return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentDto)));
        }
        throw new Exception("This Student does not exist");
    }

    @Override
    public Optional<StudentDto> findById(Long id) throws EntityExistsException {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        return studentEntity.map(studentMapper::toDto);
    }

    @Override
    public void deleteStudent(Long id) throws Exception {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if (studentEntity.isEmpty()) {
            throw new Exception("Invalid student ID");
        }
        studentRepository.delete(studentEntity.get());
    }


    @Override
    public Page<StudentDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
        Page<StudentDto> students = studentRepository.findAll(pageable).map(studentMapper::toDto);
        return students;
    }

    @Override
    public List<StudentDto> findStudentsByFirstName(String name) {
       // return studentRepository.findStudentsByName(name).stream().map(studentMapper::toDto).collect(Collectors.toList());

        List<StudentEntity> students = studentRepository.findAll();
        List<StudentDto> dtoStudents = new ArrayList<>();

        for(StudentEntity s: students){
            dtoStudents.add(studentMapper.toDto(s));


        }
        return dtoStudents;
    }
}








