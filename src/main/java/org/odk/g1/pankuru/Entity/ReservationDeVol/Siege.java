package org.odk.g1.pankuru.Entity.ReservationDeVol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.odk.g1.pankuru.Entity.Compagnie.Avion;
import org.odk.g1.pankuru.Entity.Enum.SiegeDisponible;
// import org.springframework.security.access.method.P;

import java.util.List;

@Data
@Entity
public class Siege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private SiegeDisponible disponibilite;

    @ManyToOne
    @JoinColumn(name = "positionSiege_id")
    private PositionSiege positionSiege;
    
    @JsonIgnore
    @OneToMany(mappedBy = "siege")
    private List<Passager> passagerList;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Avion avion;

}
