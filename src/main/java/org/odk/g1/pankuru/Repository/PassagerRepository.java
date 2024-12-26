package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.ReservationDeVol.Passager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PassagerRepository extends JpaRepository<Passager,Long> {
    @Query("select p.id as id, p.nom as nom, p.prenom as prenom, p.numeroDeVisa as numeroDeVisa, p.numeroDePassPort as numeroDePassPort, ps.nom as positionSiege, s.numero as numeroSiege, c.nom as classeSiege, ps.tarif as tarifSiege, r.dateReservation as dateReservation from Passager p join p.siege s join s.positionSiege ps join ps.classeSiege c join p.reservation r where r.vol.id=:x")
    List<Map<String, Object>> tout(@Param("x") Long x);

    @Query("select p.id as id, p.nom as nom, p.prenom as prenom, p.numeroDeVisa as numeroDeVisa, p.numeroDePassPort as numeroDePassPort, ps.nom as numeroSiege, c.nom as classeSiege, ps.tarif as tarifSiege, r.dateReservation as dateReservation from Passager p join p.siege s join s.positionSiege ps join ps.classeSiege c join p.reservation r where p.id=:x")
    List<Map<String, Object>> trouverParId(@Param("x") Long id);

}
