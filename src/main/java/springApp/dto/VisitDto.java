package springApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class VisitDto {

    private UUID visId;
    private Instant dateVisit;
    private Instant timeVisit;

}
