package org.odk.g1.pankuru.Entity.Compagnie;

import java.util.List;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.Humain.SuperAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Table(name = "Compagnie")
@Data
@NoArgsConstructor
public class Compagnie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String matricule;
    @Column(nullable = false, unique = true)
    private String nom;
    private String logoUrl;
    private int codeIATA;
    private String codeICAO;
    private String numeroTelephone;
    @Email(message = "Email invalide")
    private String email;
    private String siteWeb;
    private String numeroLicence;
    private boolean isLocked = false;

    @ManyToOne
    @JoinColumn(name = "superAdmin_id")
    private SuperAdmin superAdmin;

    
    @JsonIgnore
    @OneToMany(mappedBy = "compagnie")
    private List<Contrat> contratList;

    
    @JsonIgnore
    @OneToMany(mappedBy = "compagnie")
    private List<AdminCompagnie> adminCompagnieList;
}
