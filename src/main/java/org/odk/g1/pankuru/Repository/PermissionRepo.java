package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer> {
}
