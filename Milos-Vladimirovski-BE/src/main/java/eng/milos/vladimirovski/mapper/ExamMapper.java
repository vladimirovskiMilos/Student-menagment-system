package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.ExamDto;
import eng.milos.vladimirovski.enity.ExamEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamMapper implements EntityMapper<ExamEntity, ExamDto>{


    @Override
    public ExamEntity toEntity(ExamDto dto) {
        return new ExamEntity(dto.getId(), dto.getExaminationPeriod(), dto.getSubject(), dto.getProfessor(), dto.getDate());
    }

    @Override
    public ExamDto toDto(ExamEntity entity) {
        return new ExamDto(entity.getId(), entity.getExaminationPeriod(), entity.getSubject(), entity.getProfessor(), entity.getDate());
    }

    @Override
    public ExamEntity toEntityNoId(ExamDto dto) {
        return new ExamEntity(dto.getExaminationPeriod(), dto.getSubject(), dto.getProfessor(), dto.getDate());
    }

    @Override
    public ExamDto toDtoNoId(ExamEntity entity) {
        return new ExamDto(entity.getExaminationPeriod(), entity.getSubject(), entity.getProfessor(), entity.getDate());
    }
}
