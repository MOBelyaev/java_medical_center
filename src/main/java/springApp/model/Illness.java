package springApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "illness")
@Getter
@Setter
public class Illness {

    @Id
    private String illMedName;
    private String illCommName;

    @OneToMany(mappedBy = "illness",fetch = FetchType.LAZY)
    private List<Diagnose> diagnoseList = new ArrayList<>();

}
