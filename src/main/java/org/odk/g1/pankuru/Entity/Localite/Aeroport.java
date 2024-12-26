package org.odk.g1.pankuru.Entity.Localite;

import java.util.List;

import jakarta.persistence.*;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "Aeroport")
@Data
public class Aeroport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String codeIATA;
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double altitude;
    private int capaciteParking = 0;
    private int nombreDePistes = 0;
    @ManyToOne
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;

    @JsonIgnore
    @ManyToMany(mappedBy = "aeroportList")
    private List<Vol> vols;

    @ManyToOne
    @JoinColumn(name = "adminCompagnie_id")
    private AdminCompagnie adminCompagnie;
}
