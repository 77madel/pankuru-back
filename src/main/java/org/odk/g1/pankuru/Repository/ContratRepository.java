package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Compagnie.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long>{
    List<Contrat> findByCompagnieId(Integer compagnie_id);
    
}
