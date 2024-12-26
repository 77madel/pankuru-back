package org.odk.g1.pankuru.Controller;

import java.util.*;
import org.odk.g1.pankuru.Entity.Compagnie.Contrat;
import org.odk.g1.pankuru.Service.Service.ContratService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/contrat")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class ContratController {

    private final ContratService contratService;

    @PostMapping("/ajout")
    public Contrat ajouter(@RequestBody Contrat contrat){
        return contratService.ajout(contrat);
    }

    @GetMapping("/afficher")
    public List<Contrat> afficher(){
        return contratService.liste();
    } 

    @PutMapping("/modifier/{id}")
    public Contrat modifier(@PathVariable Long id,@RequestBody Contrat contrat){
        return contratService.misAJour(contrat, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id){
        contratService.supprimer(id);
    }
}
