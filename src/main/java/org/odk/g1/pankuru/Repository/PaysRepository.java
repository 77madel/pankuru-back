package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Localite.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaysRepository extends JpaRepository<Pays, Integer>{
    List<Pays> findByAdminCompagnieId(Long adminCompagnieId);
}
