package org.odk.g1.pankuru.Entity.Localite;


import java.util.List;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Ville")
@Data
@NoArgsConstructor
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "pays_id", nullable = false)
    private Pays pays;

    @JsonIgnore
    @OneToMany(mappedBy = "ville", cascade = CascadeType.REMOVE)
    private List<Aeroport> aeroportList;

    @ManyToOne
    @JoinColumn(name = "adminCompagnie_id")
    private AdminCompagnie adminCompagnie;
}
