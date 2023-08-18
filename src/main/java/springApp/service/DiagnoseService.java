package springApp.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springApp.dto.DiagnoseDto;
import springApp.model.Diagnose;
import springApp.repository.DiagnoseRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("diagnose")
public class DiagnoseService {

   private final DiagnoseRepository diagnoseRepository;

    public DiagnoseService(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }

    @PostMapping
    @Transactional
    public DiagnoseDto create(@RequestBody DiagnoseDto diagnoseDto){

        Diagnose entity = new Diagnose();
        entity.setDiagId(UUID.randomUUID());
        entity.setDegree(diagnoseDto.getDegree());
        entity.setVisit(diagnoseDto.getVisit());
        entity.setIllness(diagnoseDto.getIllness());

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
        diagnose.get().setVisit(diagnoseDto.getVisit());
        diagnose.get().setIllness(diagnoseDto.getIllness());

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
                .visit(diagnose.get().getVisit())
                .illness(diagnose.get().getIllness())
                .build();
    }
}
