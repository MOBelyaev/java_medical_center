package springApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "visit", schema = "public")
@Getter
@Setter
public class Visit {

    @Id
    private UUID visId;
    private Instant dateVisit;
    private Instant timeVisit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Physician physician;
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @OneToMany(mappedBy = "visit",fetch = FetchType.LAZY)
    private List<Diagnose> diagnoseList = new ArrayList<>();


}
