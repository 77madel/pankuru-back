package org.odk.g1.pankuru.Repository.Paiement;

import org.odk.g1.pankuru.Entity.Paiement.MobileMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileMoneyRepository extends JpaRepository<MobileMoney, String> {
}
