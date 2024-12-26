package org.odk.g1.pankuru.Entity.Humain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.odk.g1.pankuru.Entity.Compagnie.Compagnie;
import org.odk.g1.pankuru.Utils.UtilService;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdmin extends Personne{
    private String pseudo = UtilService.generateRandomPseudo();

    @JsonIgnore
    @OneToMany(mappedBy = "superAdmin")
    private List<Compagnie> compagnieList;

    @JsonIgnore
    @OneToMany(mappedBy = "superAdmin")
    private List<AdminCompagnie> adminCompagnieList;
}
