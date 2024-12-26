package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Localite.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Integer>{
    List<Ville> findByAdminCompagnieId(Long adminCompagnieId);
}
