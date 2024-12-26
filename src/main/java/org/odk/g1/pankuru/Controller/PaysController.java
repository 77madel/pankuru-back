package org.odk.g1.pankuru.Controller;

import java.util.List;

import org.odk.g1.pankuru.Entity.Localite.Pays;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.PaysService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pays")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class PaysController {
    
    private final PaysService paysService;
    private final CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Pays ajouter(@RequestBody Pays pays){
        return paysService.ajout(pays);
    }

    @GetMapping("/afficher")
    public List<Pays> afficher(){
        return compagnieService.getPaysByCompagnieId();
    } 

    @PutMapping("/modifier/{id}")
    public Pays modifier(@PathVariable Integer id,@RequestBody Pays pays){
        return paysService.misAJour(pays, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        paysService.supprimer(id);
    }
}
