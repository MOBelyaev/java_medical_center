package springApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "diagnose")
@Getter
@Setter
public class Diagnose {

    @Id
    private UUID diagId;
    private String degree;

    @ManyToOne(fetch = FetchType.LAZY)
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Illness illness;
}
