package springApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient", schema = "public")
@Getter
@Setter
public class Patient {

    @Id
    private UUID patId;
    private String patFirstName;
    private String patLastName;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private List<Visit> visitList = new ArrayList<>();

}
