package org.odk.g1.pankuru.Entity.Localite;

import java.util.List;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Pays")
@Data
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String nom;
    private String paysImageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "pays", cascade = CascadeType.REMOVE)
    private List<Ville> villeList;

    @ManyToOne
    @JoinColumn(name = "adminCompagnie_id")
    private AdminCompagnie adminCompagnie;
}
