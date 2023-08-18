package springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.dto.IllnessDto;
import springApp.model.Illness;

public interface IllnessRepository extends JpaRepository<Illness, String> {

}
