package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.ExamRegDto;
import eng.milos.vladimirovski.enity.ExamRegEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamRegMapper implements EntityMapper<ExamRegEntity, ExamRegDto>{
    @Override
    public ExamRegEntity toEntity(ExamRegDto dto) {
        return new ExamRegEntity(dto.getId(), dto.getStudent(),dto.getExam());
    }

    @Override
    public ExamRegDto toDto(ExamRegEntity entity) {
        return new ExamRegDto(entity.getId(), entity.getStudent(), entity.getExam());
    }

    @Override
    public ExamRegEntity toEntityNoId(ExamRegDto dto) {
        return new ExamRegEntity(dto.getStudent(), dto.getExam());
    }

    @Override
    public ExamRegDto toDtoNoId(ExamRegEntity entity) {
        return  new ExamRegDto(entity.getStudent(), entity.getExam());
    }
}
