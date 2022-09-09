package eng.milos.vladimirovski.mapper;

import eng.milos.vladimirovski.dto.Dto;
import eng.milos.vladimirovski.dto.SubjectDto;
import eng.milos.vladimirovski.enity.AppEntity;

public interface EntityMapper  <E extends AppEntity, D extends Dto>{

    E toEntity(D dto);
    D toDto(E entity);

    E toEntityNoId(D dto);

    D toDtoNoId(E entity);

}
