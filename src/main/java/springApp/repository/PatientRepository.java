package springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.model.Patient;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
