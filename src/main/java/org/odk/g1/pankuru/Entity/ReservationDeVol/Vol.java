package org.odk.g1.pankuru.Entity.ReservationDeVol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.odk.g1.pankuru.Entity.Compagnie.Avion;
import org.odk.g1.pankuru.Entity.Enum.StatutVol;
import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
// import org.springframework.format.annotation.DateTimeFormat;
import org.odk.g1.pankuru.Entity.Localite.Aeroport;
// import java.util.List;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroDeVol;
    @ManyToOne
    private Aeroport aeroportDepart;
    @ManyToOne
    private Aeroport aeroportDArrivee;
    private Date dateEtHeureArrivee;
    private Date dateEtHeureDepart;
    private Long tarifEconomiqueDeBase;
    @Enumerated(EnumType.STRING)
    private StatutVol satut = StatutVol.EN_COURS;
    @ManyToOne
    private Avion avionDepart;

    @JsonIgnore
    @OneToMany(mappedBy = "vol", cascade = CascadeType.REMOVE)
    private List<Reservation> reservationList;

    @ManyToMany
    @JoinTable(name = "vol_avion", 
           joinColumns = @JoinColumn(name = "vol_id"), 
           inverseJoinColumns = @JoinColumn(name = "avion_id"))
    private List<Avion> avionList;

    @ManyToMany
    @JoinTable(name = "vol_aeroport", 
           joinColumns = @JoinColumn(name = "vol_id"), 
           inverseJoinColumns = @JoinColumn(name = "aeroport_id"))
    private List<Aeroport> aeroportList;

    @ManyToOne
    @JoinColumn(name = "adminCompagnie_id")
    private AdminCompagnie adminCompagnie;

}
