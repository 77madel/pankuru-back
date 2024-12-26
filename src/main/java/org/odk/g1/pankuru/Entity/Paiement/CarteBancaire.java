package org.odk.g1.pankuru.Entity.Paiement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarteBancaire extends ModePaiement{
    @Column(unique=true, nullable = false)
    private String numeroDeCarte;
    @Column(nullable = false)
    private String nomTitulaire;
    @Column(nullable = false)
    private String dateExpiration;
    @Column(nullable = false)
    private String typeCarte;
    private String adresseFacturation;
}
