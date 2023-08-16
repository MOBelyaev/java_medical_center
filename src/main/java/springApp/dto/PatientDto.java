package springApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PatientDto {

    private UUID patId;
    private String patFirstName;
    private String patLastName;

}
