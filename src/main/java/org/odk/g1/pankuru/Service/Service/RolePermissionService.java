package org.odk.g1.pankuru.Service.Service;

import java.util.ArrayList;

import org.odk.g1.pankuru.Entity.Enum.EnumPermission;
import org.odk.g1.pankuru.dto.RolePermissionDTO;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

import org.odk.g1.pankuru.Repository.RolePermissionRepo;


@Service
@AllArgsConstructor
public class RolePermissionService {
    private RolePermissionRepo rolePermissionRepository;

    public List<RolePermissionDTO> getAllRolePermissions() {
        List<Object[]> results = rolePermissionRepository.findRolePermissions();
        List<RolePermissionDTO> dtos = new ArrayList<>();

        for (Object[] result : results) {
            String roleName = (String) result[0];
            String permissionEndpoint = (String) result[1];
            EnumPermission permissionPermission = (EnumPermission) result[2];

            RolePermissionDTO dto = new RolePermissionDTO(roleName, permissionEndpoint, permissionPermission);
            dtos.add(dto);
        }

        return dtos;
    }

}
