package org.odk.g1.pankuru.Repository;

import org.odk.g1.pankuru.Entity.Permission.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RolePermissionRepo extends JpaRepository<Role, Long> {
    @Query("SELECT r.nom AS roleName, p.endPoint AS permissionEndpoint, p.permission AS permissionPermission " +
           "FROM Role r JOIN r.permissionList p")
    List<Object[]> findRolePermissions();




}
