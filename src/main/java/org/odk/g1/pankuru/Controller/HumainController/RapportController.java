package org.odk.g1.pankuru.Controller.HumainController;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.Rapport;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.HumainService.RapportService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rapport")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class RapportController {
    
    private final RapportService rapportService;
    private final CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Rapport ajouter(@RequestBody Rapport rapport){
        return rapportService.ajout(rapport);
    }

    @GetMapping("/afficher")
    public List<Rapport> afficher(){
        return compagnieService.getRapportByCompagnie();
    }

    @GetMapping("/afficher/{id}")
    public Optional<Rapport> trouverParId(@PathVariable Long id){
        return rapportService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Rapport modifier(@PathVariable Long id,@RequestBody Rapport rapport){
        return rapportService.misAJour(rapport, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id){
        rapportService.supprimer(id);
    }
}
