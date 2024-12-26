package org.odk.g1.pankuru.Entity.ReservationDeVol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PositionSiege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private double tarif;

    @ManyToOne
    @JoinColumn(name = "classeSiege_id", nullable = false)
    private ClasseSiege classeSiege;
    
    @JsonIgnore
    @OneToMany(mappedBy = "positionSiege")
    private List<Siege> siege;
}
