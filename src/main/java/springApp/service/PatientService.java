package springApp.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springApp.dto.PatientDto;
import springApp.model.Patient;
import springApp.repository.PatientRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("patient")
public class PatientService {

   private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping
    @Transactional
    public PatientDto create(@RequestBody PatientDto patientDto){

        Patient entity = new Patient();
        entity.setPatId(UUID.randomUUID());
        entity.setPatFirstName(patientDto.getPatFirstName());
        entity.setPatLastName(patientDto.getPatLastName());

        Patient saved = patientRepository.save(entity);
        patientDto.setPatId(saved.getPatId());

        return patientDto;

    }

    @PutMapping
    @Transactional
    public void update(@RequestBody PatientDto patientDto){

        Optional<Patient> patient = patientRepository.findById(patientDto.getPatId());
        if(patient.isEmpty()){
            throw new EntityNotFoundException("Пациента с данным id: " + patientDto.getPatId() + " не найдено.");
        }
        patient.get().setPatId(patientDto.getPatId());
        patient.get().setPatFirstName(patientDto.getPatFirstName());
        patient.get().setPatLastName(patientDto.getPatLastName());
    }

    @GetMapping("/{patId}")
    @Transactional
    public PatientDto read(@PathVariable UUID patId){

        Optional<Patient> patient = patientRepository.findById(patId);
        if(patient.isEmpty()){
            throw new EntityNotFoundException("Пациента с данным id: " + patId + " не найдено. ");
        }

        return PatientDto.builder()
                .patId(patient.get().getPatId())
                .patFirstName(patient.get().getPatFirstName())
                .patLastName(patient.get().getPatLastName())
                .build();
    }
}
