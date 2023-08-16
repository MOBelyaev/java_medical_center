package springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.model.Illness;

public interface IllnessRepository extends JpaRepository<Illness, String> {

}
