package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.SubjectEntity;
import org.springframework.stereotype.Component;


@Component
public class SubjectMapper implements EntityMapper<SubjectEntity, SubjectDto>{


    @Override
    public SubjectEntity toEntity(SubjectDto dto) {
       return new SubjectEntity(dto.getId(), dto.getName(), dto.getDescription(), dto.getNoOfESP(),
               dto.getYearOfStudy(), dto.getSemester());
    }

    @Override
    public SubjectDto toDto(SubjectEntity entity) {
        return new SubjectDto(entity.getId(), entity.getName(),
         entity.getDescription(), entity.getNoOfESP(), entity.getYearOfStudy(), entity.getSemester());
    }




    @Override
    public SubjectEntity toEntityNoId(SubjectDto dto) {
        return new SubjectEntity(dto.getName(), dto.getDescription(), dto.getNoOfESP(),
                dto.getYearOfStudy(), dto.getSemester());
    }

    @Override
    public SubjectDto toDtoNoId(SubjectEntity entity) {
        return new SubjectDto(entity.getName(),
                entity.getDescription(), entity.getNoOfESP(), entity.getYearOfStudy(), entity.getSemester());
    }
}
