package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Visitor.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
}
