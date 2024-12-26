package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VolRepository extends JpaRepository<Vol,Long> {
    List<Vol> findByAdminCompagnieId(Long adminCompagnieId);

    @Query("select v from Vol v where v.aeroportDepart=:d AND v.aeroportDArrivee=:a ")
    List<Vol> searchVol(@Param("d")String aeroportDepart, @Param("a")String aeroportDArrivee);
}
