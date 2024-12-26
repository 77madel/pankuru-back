package org.odk.g1.pankuru.dto;

import lombok.Getter;
import org.odk.g1.pankuru.Entity.Enum.EnumPermission;

@Getter
public class RolePermissionDTO {
    private final String roleName;
    private final String permissionEndpoint;
    private final EnumPermission permissionPermission;

    public RolePermissionDTO(String roleName, String permissionEndpoint, EnumPermission permissionPermission) {
        this.roleName = roleName;
        this.permissionEndpoint = permissionEndpoint;
        this.permissionPermission = permissionPermission;
    }

    // getters et setters

    @Override
    public String toString() {
        return "RolePermissionDTO{" +
               "roleName='" + roleName + '\'' +
               ", permissionEndpoint='" + permissionEndpoint + '\'' +
               ", permissionPermission='" + permissionPermission + '\'' +
               '}';
    }
}
