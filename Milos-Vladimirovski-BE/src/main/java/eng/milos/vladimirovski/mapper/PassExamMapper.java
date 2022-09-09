package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.PassExamDto;
import eng.milos.vladimirovski.enity.PassExamEntity;
import org.springframework.stereotype.Component;


@Component
public class PassExamMapper implements EntityMapper<PassExamEntity, PassExamDto>{
    @Override
    public PassExamEntity toEntity(PassExamDto dto) {
        return new PassExamEntity(dto.getId(), dto.getExamRegistration(), dto.getGrade());
    }

    @Override
    public PassExamDto toDto(PassExamEntity entity) {
        return new PassExamDto(entity.getId(), entity.getExamRegistration(), entity.getGrade());
    }

    @Override
    public PassExamEntity toEntityNoId(PassExamDto dto) {
        return new PassExamEntity(dto.getExamRegistration(), dto.getGrade());
    }

    @Override
    public PassExamDto toDtoNoId(PassExamEntity entity) {
        return new PassExamDto(entity.getExamRegistration(), entity.getGrade());
    }
}
