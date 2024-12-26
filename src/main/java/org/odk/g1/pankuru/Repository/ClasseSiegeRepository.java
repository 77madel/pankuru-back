package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.ReservationDeVol.ClasseSiege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseSiegeRepository extends JpaRepository<ClasseSiege,Integer> {
    ClasseSiege findByNom(String nom);
}
