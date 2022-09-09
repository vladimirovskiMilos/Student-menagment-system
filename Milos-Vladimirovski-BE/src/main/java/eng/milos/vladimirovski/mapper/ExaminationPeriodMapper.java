package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.ExaminationPeriodDto;
import eng.milos.vladimirovski.enity.ExaminationPeriodEntity;
import org.springframework.stereotype.Component;

@Component
public class ExaminationPeriodMapper implements EntityMapper<ExaminationPeriodEntity, ExaminationPeriodDto>{
    @Override
    public ExaminationPeriodEntity toEntity(ExaminationPeriodDto dto) {
       return new ExaminationPeriodEntity(dto.getId(), dto.getStart(), dto.getEnd(), dto.getName(),  dto.isActive());
    }

    @Override
    public ExaminationPeriodDto toDto(ExaminationPeriodEntity entity) {
        return new ExaminationPeriodDto(entity.getId(), entity.getStart(), entity.getEnd(), entity.getName(), entity.isActive());
    }

    @Override
    public ExaminationPeriodEntity toEntityNoId(ExaminationPeriodDto dto) {
        return new ExaminationPeriodEntity(dto.getStart(), dto.getEnd(), dto.getName(),  dto.isActive());
    }

    @Override
    public ExaminationPeriodDto toDtoNoId(ExaminationPeriodEntity entity) {
        return new ExaminationPeriodDto(entity.getStart(), entity.getEnd(), entity.getName(), entity.isActive());
    }
}
