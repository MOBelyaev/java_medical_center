package springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.model.Diagnose;

import java.util.UUID;

public interface DiagnoseRepository extends JpaRepository<Diagnose, UUID> {
}
