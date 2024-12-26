package org.odk.g1.pankuru.Repository;

import java.util.List;

import org.odk.g1.pankuru.Entity.Localite.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeroportRepository extends JpaRepository<Aeroport, Long>{
    List<Aeroport> findByAdminCompagnieId(Long adminCompagnieId);
    
}
