package org.odk.g1.pankuru.Controller.HumainController;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Service.Service.HumainService.AdminCompagnieService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admincompagnie")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class AdminCompagnieController {
    private final AdminCompagnieService adminCompagnieService;

    @PostMapping("/ajout")
    public AdminCompagnie ajouter(@RequestBody AdminCompagnie adminCompagnie){
        return adminCompagnieService.ajout(adminCompagnie);
    }

    @GetMapping("/afficher")
    public List<AdminCompagnie> afficher(){
        return adminCompagnieService.liste();
    } 

    @GetMapping("/afficher/{id}")
    public Optional<AdminCompagnie> trouverParId(@PathVariable("id") Long id){
        return adminCompagnieService.trouverParId(id);
    } 

    @PutMapping("/modifier/{id}")
    public AdminCompagnie modifier(@PathVariable Long id,@RequestBody AdminCompagnie adminCompagnie){
        return adminCompagnieService.misAJour(adminCompagnie, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id){
        adminCompagnieService.supprimer(id);
    }
    
}
