package org.odk.g1.pankuru.Entity.ReservationDeVol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bagage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Float poids;
    private String dimension;

    @ManyToOne
    @JoinColumn(name = "passager_id", nullable = false)
    private Passager passager;

    @ManyToOne
    @JoinColumn(name = "typeBagage_id")
    private TypeBagage typeBagage;
}
