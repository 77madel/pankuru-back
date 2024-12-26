package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Permission.Permission;
import org.odk.g1.pankuru.Repository.PermissionRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService implements CrudService<Permission, Integer> {
    private PermissionRepo permissionRepo;

    @Override
    public Permission ajout(Permission entity) {
        return permissionRepo.save(entity);
    }

    @Override
    public List<Permission> liste() {
        return permissionRepo.findAll();
    }

    @Override
    public Optional<Permission> trouverParId(Integer aLong) {
        return permissionRepo.findById(aLong);
    }

    @Override
    public Permission misAJour(Permission entity, Integer Id) {
        Optional<Permission> permission = permissionRepo.findById(Id);
        Permission permission1;
        if (permission.isEmpty()){
            throw new RuntimeException("L'permission recherché est introuvable");
        }else{
            permission1 = permission.get();
            if(permission1.getPermission() != null) permission1.setPermission(entity.getPermission());
            if(permission1.getEndPoint() != null) permission1.setEndPoint(entity.getEndPoint());
            return permissionRepo.save(permission1);
        }
    }

    @Override
    public void supprimer(Integer aLong) {
        permissionRepo.deleteById(aLong);
    }
}
