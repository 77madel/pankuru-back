package org.odk.g1.pankuru.Repository.Paiement;

import org.odk.g1.pankuru.Entity.Paiement.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
