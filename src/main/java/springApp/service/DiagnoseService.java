package springApp.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springApp.dto.DiagnoseDto;
import springApp.model.Diagnose;
import springApp.model.Illness;
import springApp.repository.DiagnoseRepository;
import springApp.repository.IllnessRepository;
import springApp.repository.VisitRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("diagnose")
public class DiagnoseService {

   private final DiagnoseRepository diagnoseRepository;
   private final IllnessRepository illnessRepository;
   private final VisitRepository visitRepository;

    public DiagnoseService(DiagnoseRepository diagnoseRepository,
                           IllnessRepository illnessRepository,
                           VisitRepository visitRepository)
    {
        this.diagnoseRepository = diagnoseRepository;
        this.illnessRepository = illnessRepository;
        this.visitRepository = visitRepository;
    }

    @PostMapping
    @Transactional
    public DiagnoseDto create(@RequestBody DiagnoseDto diagnoseDto){

        Optional<Illness> illness = illnessRepository.findById(diagnoseDto.getIllMedName());

        Diagnose entity = new Diagnose();
        entity.setDiagId(UUID.randomUUID());
        entity.setDegree(diagnoseDto.getDegree());

        illness.ifPresent(entity::setIllness);

      //  entity.setVisit();
       // entity.setIllMedName();

        Diagnose saved = diagnoseRepository.save(entity);
        diagnoseDto.setDiagId(saved.getDiagId());

        return diagnoseDto;
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody DiagnoseDto diagnoseDto){

        Optional<Diagnose> diagnose = diagnoseRepository.findById(diagnoseDto.getDiagId());
        if(diagnose.isEmpty()){
            throw new EntityNotFoundException("Диагноза с данным id:" + diagnoseDto.getDiagId() + " не найдено.");
        }
        diagnose.get().setDegree(diagnoseDto.getDegree());
       // diagnose.get().setVisit(diagnoseDto.getVisit());
       // diagnose.get().setIllMedName(diagnoseDto.getIllMedName());

    }

    @GetMapping("/{diagId}")
    public DiagnoseDto read(@PathVariable UUID diagId){

        Optional<Diagnose> diagnose = diagnoseRepository.findById(diagId);
        if(diagnose.isEmpty()){
            throw new EntityNotFoundException("Диагноза с данным id:" + diagId + " не найдено.");
        }

        return DiagnoseDto.builder()
                .diagId(diagnose.get().getDiagId())
                .degree(diagnose.get().getDegree())
                .build();
    }
}
