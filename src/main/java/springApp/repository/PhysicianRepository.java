package springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.model.Physician;

import java.util.UUID;

public interface PhysicianRepository extends JpaRepository<Physician, UUID> {
}
