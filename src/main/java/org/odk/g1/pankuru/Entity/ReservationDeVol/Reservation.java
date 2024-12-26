package org.odk.g1.pankuru.Entity.ReservationDeVol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
// import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.odk.g1.pankuru.Entity.Enum.Statut;
import org.odk.g1.pankuru.Entity.Humain.Utilisateur;
import org.odk.g1.pankuru.Entity.Paiement.Paiement;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReservation = LocalDate.now();
    private int nombreDepassager = 1;
    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;
    private Date dateAnnulation;
    private String raisonAnnulation;

    @ManyToOne
    @JoinColumn(name = "vol_id", nullable = false)
    private Vol vol;

    @JsonIgnore
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.REMOVE)
    private List<Passager> passagerList;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @JsonIgnore
    @OneToOne(mappedBy = "reservation")
    private Paiement paiement;

}
