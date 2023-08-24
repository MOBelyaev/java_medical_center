package springApp.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springApp.dto.VisitDto;
import springApp.dto.DiagnoseDto;
import springApp.model.Diagnose;
import springApp.model.Patient;
import springApp.model.Physician;
import springApp.model.Visit;
import springApp.repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("visit")
public class VisitService {

    private final List<Diagnose> diagList = null;
    private final DiagnoseRepository diagnoseRepository;
    private final IllnessRepository illnessRepository;
    private final VisitRepository visitRepository;

    private final PhysicianRepository physicianRepository;

    private final PatientRepository patientRepository;

    public VisitService(DiagnoseRepository diagnoseRepository,
                        IllnessRepository illnessRepository,
                        VisitRepository visitRepository,
                        PhysicianRepository physicianRepository,
                        PatientRepository patientRepository)
    {
        this.diagnoseRepository = diagnoseRepository;
        this.illnessRepository = illnessRepository;
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.physicianRepository = physicianRepository;
    }


    @PostMapping
    @Transactional
    public VisitDto create(@RequestBody VisitDto visitDto){

        Visit entity = new Visit();
        entity.setVisId(UUID.randomUUID());
        entity.setDateVisit(visitDto.getDateVisit());
        entity.setTimeVisit(visitDto.getTimeVisit());

        Optional<Patient> patientById = patientRepository.findById(visitDto.getPatId());
        Optional<Physician> physicianById = physicianRepository.findById(visitDto.getPhyId());


        patientById.ifPresent(entity::setPatient);
        physicianById.ifPresent(entity::setPhysician);


        Visit saved = visitRepository.save(entity);
        visitDto.setVisId(saved.getVisId());

        return visitDto;
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

    @GetMapping("/{visId}")
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
