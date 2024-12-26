package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Permission.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNom(String id);
    
}
