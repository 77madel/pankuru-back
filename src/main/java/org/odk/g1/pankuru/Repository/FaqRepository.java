package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Humain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByAdminCompagnieId(Long adminCompagnieId);
}