package org.odk.g1.pankuru.Repository.HumainRepo;

import org.odk.g1.pankuru.Entity.Humain.Personne;
import org.odk.g1.pankuru.Entity.Permission.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneRepo extends JpaRepository<Personne, Long> {
    Optional<Personne> findByEmail(String email);
    List<Role> findByIdAndRole(Long id, Role role);
}
