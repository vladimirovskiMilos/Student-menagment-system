package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.ProfessorDto;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import org.springframework.stereotype.Component;


@Component
public class ProfessorMapper implements EntityMapper<ProfessorEntity, ProfessorDto>{
    @Override
    public ProfessorEntity toEntity(ProfessorDto dto) {
        return new ProfessorEntity(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress(),
                dto.getPhone(), dto.getCity(), dto.getTitle(), dto.getReelectionDate(), dto.getSubjects());
    }

    @Override
    public ProfessorDto toDto(ProfessorEntity entity) {

        return new ProfessorDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getAddress(), entity.getPhone(), entity.getCity(), entity.getTitle(), entity.getReelectionDate(),
                entity.getSubjects());
    }

    @Override
    public ProfessorEntity toEntityNoId(ProfessorDto dto) {
        return new ProfessorEntity(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress(), dto.getPhone(),
                dto.getCity(), dto.getTitle(), dto.getReelectionDate(), dto.getSubjects());

    }

    @Override
    public ProfessorDto toDtoNoId(ProfessorEntity entity) {
        return new ProfessorDto(entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getAddress(),
                entity.getPhone(), entity.getCity(), entity.getTitle(), entity.getReelectionDate(),
                entity.getSubjects());
    }

}
