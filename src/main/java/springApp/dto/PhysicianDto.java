package springApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PhysicianDto {

    private UUID phyId;
    private String phyTitle;
    private String phyFirstName;
    private String phyLastName;

}
