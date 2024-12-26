package org.odk.g1.pankuru.Entity.Compagnie;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contrat")
@Data
@NoArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private String cgu;
    private String commentaires;

    @ManyToOne
    @JoinColumn(name = "compagnie_id", nullable = false)
    @JsonIgnore
    private Compagnie compagnie;
}
