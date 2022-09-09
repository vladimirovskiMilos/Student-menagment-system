package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.StudentDto;
import eng.milos.vladimirovski.enity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements EntityMapper<StudentEntity, StudentDto>{
    @Override
    public StudentEntity toEntity(StudentDto dto) {
        return new StudentEntity(dto.getId(), dto.getEmail(), dto.getIndexNumber(),
                dto.getIndexYear(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getCity(), dto.getCurrentYearOfStudy());
    }

    @Override
    public StudentDto toDto(StudentEntity entity) {
        return new StudentDto(entity.getId(), entity.getEmail(), entity.getIndexNumber(), entity.getIndexYear(),
                entity.getFirstName(), entity.getLastName(), entity.getCity(), entity.getCurrentYearOfStudy(), entity.getAddress());
    }



    @Override
    public StudentEntity toEntityNoId(StudentDto dto) {
        return new StudentEntity( dto.getEmail(), dto.getIndexNumber(), dto.getIndexYear(), dto.getFirstName(),
                dto.getLastName(), dto.getAddress(), dto.getCity(), dto.getCurrentYearOfStudy());
    }

    @Override
    public StudentDto toDtoNoId(StudentEntity entity) {
        return new StudentDto(entity.getEmail(), entity.getIndexNumber(), entity.getIndexYear(), entity.getFirstName(),
                 entity.getLastName(), entity.getCity(), entity.getCurrentYearOfStudy(), entity.getAddress());
    }
}
