package springApp.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springApp.dto.DiagnoseDto;
import springApp.dto.IllnessDto;
import springApp.model.Diagnose;
import springApp.model.Illness;
import springApp.repository.IllnessRepository;

import javax.persistence.EntityNotFoundException;
import java.sql.Connection;
import java.util.Optional;

@RestController
@RequestMapping("illness")
public class IllnessService {

    private final IllnessRepository illnessRepository;

    public IllnessService(IllnessRepository illnessRepository) {
        this.illnessRepository = illnessRepository;

    }

    @PostMapping
    @Transactional
    public IllnessDto create(@RequestBody IllnessDto illnessDto){

        Illness entity = new Illness();
        entity.setIllMedName(illnessDto.getIllMedName());
        entity.setIllCommonName(illnessDto.getIllCommName());

        Illness saved = illnessRepository.save(entity);
        illnessDto.setIllMedName(saved.getIllMedName());

        return illnessDto;

    }

    @PutMapping
    @Transactional
    public void update(@RequestBody IllnessDto illnessDto){

        Optional<Illness> illness = illnessRepository.findById(illnessDto.getIllMedName());
        if(illness.isEmpty()){
            throw new EntityNotFoundException("Заболевания с данным именем: " + illnessDto.getIllMedName() + " не найдено.");
        }
        illness.get().setIllCommonName(illnessDto.getIllCommName());

    }


    @GetMapping("/{IllMedName}")
    @Transactional
    public IllnessDto read(@PathVariable String IllMedName) {

        Optional<Illness> illness = illnessRepository.findById(IllMedName);
        if (illness.isEmpty()) {
            throw new EntityNotFoundException("Заболевания с данным именем: " + IllMedName + " не найдено.");
        }

        return IllnessDto.builder()
                .illMedName(illness.get().getIllMedName())
                .illCommName(illness.get().getIllCommonName())
                .build();

    }

}
