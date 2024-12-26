package org.odk.g1.pankuru.Controller;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Localite.Aeroport;
import org.odk.g1.pankuru.Service.Service.AeroportService;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/aeroport")
@AllArgsConstructor
public class AeroportController {
    private final AeroportService aeroportService;
    private final CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Aeroport ajouter(@RequestBody Aeroport aeroport){
        return aeroportService.ajout(aeroport);
    }

    @GetMapping("/afficher")
    public List<Aeroport> afficher(){
        return compagnieService.getAeroportsByCompagnieId();
    }

    @GetMapping("/afficher/{id}")
    public Optional<Aeroport> trouverParId(@PathVariable Long id){
        return aeroportService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Aeroport modifier(@PathVariable Long id,@RequestBody Aeroport aeroport){
        return aeroportService.misAJour(aeroport, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id){
        aeroportService.supprimer(id);
    }
}
