package eng.milos.vladimirovski.service.impl;

import eng.milos.vladimirovski.dto.PassExamDto;
import eng.milos.vladimirovski.enity.PassExamEntity;
import eng.milos.vladimirovski.mapper.PassExamMapper;
import eng.milos.vladimirovski.repository.PassExamRepository;
import eng.milos.vladimirovski.service.PassExamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassExamServiceImpl implements PassExamService {

    private final PassExamRepository passExamRepository;
    private final PassExamMapper passExamMapper;

    @Override
    public List<PassExamDto> getAll() {
        List<PassExamEntity> exams = passExamRepository.findAll();
        return  exams.stream().map(passExamMapper::toDto).collect(Collectors.toList());

    /*    List<PassExamDto> passDtos = new ArrayList<>();

        for(PassExamEntity d : exams){
            passDtos.add(passExamMapper.toDto(d));
        }
        return passDtos;*/
    }

    @Override
    public Optional<PassExamDto> findById(Long id) {
       Optional<PassExamEntity> exam = passExamRepository.findById(id);

       return exam.map(passExamMapper::toDto);
    }

    @Override
    public PassExamDto add(PassExamDto entity) throws EntityExistsException {
            /*List<PassExamEntity> passedExams = passExamRepository.findAll();
                for (PassExamEntity e : passedExams){
                    if(e.getId() == entity.getId()){
                        throw new EntityExistsException("You cannot register this passed exam is already saved!");
                    }
                }*/
        return passExamMapper.toDtoNoId(passExamRepository.save(passExamMapper.toEntityNoId(entity)));
    }

    @Override
    public PassExamDto edit(PassExamDto passExamDto) throws Exception {
        if(passExamRepository.existsById(passExamDto.getId())){
            return passExamMapper.toDto(passExamRepository.save(passExamMapper.toEntity(passExamDto)));
        }
        throw  new Exception("You cannot edit this passed exam. Passed exam does not exists");
    }

    /* if (repository.existsById(examReg.getId())) {
            return mapper.toDto(repository.save(mapper.toEntity(examReg)));
        }
        throw new Exception("This exam registration does not exist");*/
}
