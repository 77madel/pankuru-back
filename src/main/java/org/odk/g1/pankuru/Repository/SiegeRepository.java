package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.ReservationDeVol.Siege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiegeRepository extends JpaRepository<Siege,Long> {
    List<Siege> findByAvionId(Long avionId);
}
