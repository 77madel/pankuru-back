package org.odk.g1.pankuru.Entity.Humain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import org.odk.g1.pankuru.Entity.Compagnie.Avion;
import org.odk.g1.pankuru.Entity.Compagnie.Compagnie;
import org.odk.g1.pankuru.Entity.Localite.Aeroport;
import org.odk.g1.pankuru.Entity.Localite.Pays;
import org.odk.g1.pankuru.Entity.Localite.Ville;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.odk.g1.pankuru.Utils.UtilService;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCompagnie extends Personne {
    private String pseudo = UtilService.generateRandomPseudo();

    @ManyToOne
    @JoinColumn(name = "compagnie_id", nullable = false)
    private Compagnie compagnie;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Rapport> rapportList;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Faq> faqList;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Aeroport> aeroportList;


    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Pays> paysList;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Ville> villeList;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Vol> volList;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Avion> avionList;

    @JsonIgnore
    @OneToMany(mappedBy = "adminCompagnie")
    private List<Personnel> personnelList;

    @ManyToOne
    @JoinColumn(name = "superadmin_id")
    private SuperAdmin superAdmin;


}
