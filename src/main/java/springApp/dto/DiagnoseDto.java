package springApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DiagnoseDto {

    private UUID diagId;
    private String degree;
    private UUID visitId;
    private String illMedName;

}
