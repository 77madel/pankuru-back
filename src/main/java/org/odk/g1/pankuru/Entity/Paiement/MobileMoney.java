package org.odk.g1.pankuru.Entity.Paiement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MobileMoney extends ModePaiement{
    @Column(nullable = false, unique = true)
    private String numeroDeTelephone;
}
