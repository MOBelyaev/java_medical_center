package springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.model.Visit;

import java.util.UUID;

public interface VisitRepository extends JpaRepository<Visit, UUID> {

}
