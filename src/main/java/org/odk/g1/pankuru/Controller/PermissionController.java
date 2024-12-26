package org.odk.g1.pankuru.Controller;

import org.odk.g1.pankuru.Entity.Permission.Permission;
import org.odk.g1.pankuru.Service.Service.PermissionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/permission")
@AllArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/ajout")
    public Permission ajouter(@RequestBody Permission permission){
        return permissionService.ajout(permission);
    }

    @GetMapping("/afficher")
    public List<Permission> afficher(){
        return permissionService.liste();
    }

    @PutMapping("/modifier/{id}")
    public Permission modifier(@PathVariable Integer id,@RequestBody Permission permission){
        return permissionService.misAJour(permission, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        permissionService.supprimer(id);
    }
}
