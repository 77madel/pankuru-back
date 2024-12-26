package org.odk.g1.pankuru.Entity.Humain;

import jakarta.persistence.*;
import lombok.Data;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Reservation;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String objet;
    private LocalDate date;
    private LocalTime heure;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "vol_id")
    private Vol vol;

}
