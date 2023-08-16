package springApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "physician")
@Getter
@Setter
public class Physician {

    @Id
    private UUID phyId;
    private String phyTitle;
    private String phyFirstName;
    private String phyLastName;

    @OneToMany(mappedBy = "physician",fetch = FetchType.LAZY)
    private List<Visit> visitList = new ArrayList<>();

}
