package springApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class IllnessDto {

    private String illMedName;
    private String illCommName;

}
