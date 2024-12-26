package org.odk.g1.pankuru.Repository.HumainRepo;

import org.odk.g1.pankuru.Entity.Humain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    @Query("select u.nom as nom, u.prenom as prenom, u.numeroDePassport as numeroDePassport, u.numeroDeVisa as numeroDeVisa, u.pointDeFideliter as pointDeFideliter, u.numeroDeTelephone as numeroDeTelephone,u.adresse as adresse, r.nom as role from Utilisateur u join u.role r")
    List<Map<String, Object>> tout();

    @Query("select u.nom as nom, u.prenom as prenom, u.numeroDePassport as numeroDePassport, u.numeroDeVisa as numeroDeVisa, u.pointDeFideliter as pointDeFideliter, u.numeroDeTelephone as numeroDeTelephone, u.adresse as adrese, r.nom as role from Utilisateur u join u.role r join u.adresse a where u.id=:x")
    List<Map<String, Object>> trouverParId(@Param("x") Long id);
}
