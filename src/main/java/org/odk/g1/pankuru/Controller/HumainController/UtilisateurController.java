package org.odk.g1.pankuru.Controller.HumainController;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.Utilisateur;
import org.odk.g1.pankuru.Service.Service.HumainService.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/utilisateur")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class UtilisateurController {
    
    private final UtilisateurService utilisateurService;

    @PostMapping("/ajout")
    public Utilisateur ajouter(@RequestBody Utilisateur utilisateur){
        return utilisateurService.ajout(utilisateur);
    }

    @GetMapping("/afficher")
    public List<Utilisateur> afficher(){
        return utilisateurService.liste();
    }

    @GetMapping("/afficher/vol")
    public List<Utilisateur> afficher2(){
        return utilisateurService.liste();
    }

    @GetMapping("/afficher/{id}")
    public Optional<Utilisateur> afficherId(@PathVariable Long id){
        return utilisateurService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Utilisateur modifier(@PathVariable Long id,@RequestBody Utilisateur utilisateur){
        return utilisateurService.misAJour(utilisateur, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id){
        utilisateurService.supprimer(id);
    }
}
