package org.odk.g1.pankuru.Controller;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Localite.Ville;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.VilleService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ville")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class VilleController {
    
    private final VilleService villeService;
    private final CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Ville ajouter(@RequestBody Ville ville){
        return villeService.ajout(ville);
    }

    @GetMapping("/afficher")
    public List<Ville> afficher(){
        return compagnieService.getVilleByCompagnie();
    }

    @GetMapping("/afficher/tout")
    public List<Ville> afficherTout(){
        return villeService.liste();
    }

    @GetMapping("/afficher/tout/{id}")
    public Optional<Ville> afficherUn(@PathVariable Integer id){
        return villeService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Ville modifier(@PathVariable Integer id,@RequestBody Ville ville){
        return villeService.misAJour(ville, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        villeService.supprimer(id);
    }
}
