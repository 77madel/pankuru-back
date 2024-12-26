package org.odk.g1.pankuru.Repository.HumainRepo;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminCompagnieRepo extends JpaRepository<AdminCompagnie, Long> {
    Optional<AdminCompagnie> findByEmail(String email);
    @Query("select a.compagnie.id from AdminCompagnie a where a.id = :adminId")
    Integer findCompagnieIdByAdminId(Long adminId);
}
