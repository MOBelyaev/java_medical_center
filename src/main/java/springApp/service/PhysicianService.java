package springApp.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springApp.dto.PhysicianDto;
import springApp.model.Physician;
import springApp.repository.PhysicianRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("physician")
public class PhysicianService {

    private final PhysicianRepository physicianRepository;

    public PhysicianService(PhysicianRepository physicianRepository) {
        this.physicianRepository = physicianRepository;
    }

    @PostMapping
    @Transactional
    public PhysicianDto create(@RequestBody PhysicianDto physicianDto){

        Physician entity = new Physician();
        entity.setPhyId(UUID.randomUUID());
        entity.setPhyTitle(physicianDto.getPhyTitle());
        entity.setPhyFirstName(physicianDto.getPhyFirstName());
        entity.setPhyLastName(physicianDto.getPhyLastName());

        Physician saved = physicianRepository.save(entity);
        physicianDto.setPhyId(saved.getPhyId());

        return physicianDto;
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody PhysicianDto physicianDto){

        Optional<Physician> physician = physicianRepository.findById(physicianDto.getPhyId());
        if(physician.isEmpty()){
            throw new EntityNotFoundException("Врача с данным id: " + physicianDto.getPhyId() + " не найдено.");
        }
        physician.get().setPhyTitle(physicianDto.getPhyTitle());
        physician.get().setPhyFirstName(physicianDto.getPhyFirstName());
        physician.get().setPhyLastName(physicianDto.getPhyLastName());

    }

    @GetMapping("/{phyId}")
    @Transactional
    public PhysicianDto read(@PathVariable UUID phyId){

        Optional<Physician> physician = physicianRepository.findById(phyId);
        if(physician.isEmpty()){
            throw new EntityNotFoundException("Врача с данным id: " + phyId + " не найдено!");
        }

        return PhysicianDto.builder()
                .phyId(phyId)
                .phyTitle(physician.get().getPhyTitle())
                .phyFirstName(physician.get().getPhyFirstName())
                .phyLastName(physician.get().getPhyLastName())
                .build();

    }

}
