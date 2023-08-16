package springApp.dto;

import lombok.Builder;
import lombok.Data;
import springApp.model.Illness;
import springApp.model.Visit;

import java.util.UUID;

@Data
@Builder
public class DiagnoseDto {

    private UUID diagId;
    private String degree;
    private Visit visit;
    private Illness illness;

}
