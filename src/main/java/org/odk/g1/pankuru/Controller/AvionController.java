package org.odk.g1.pankuru.Controller;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Compagnie.Avion;
import org.odk.g1.pankuru.Service.Service.AvionService;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/avion")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AvionController {
    
    private final AvionService avionService;
    private final CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Avion ajouter(@RequestBody Avion avion){
        return avionService.ajout(avion);
    }

    @GetMapping("/afficher")
    public List<Avion> afficher(){
        return compagnieService.getAvionByCompagnie();
    } 

    @GetMapping("/afficher/{id}")
    public Optional<Avion> afficher1(@PathVariable("id") Integer id){
        return avionService.trouverParId(id);
    } 

    @PutMapping("/modifier/{id}")
    public Avion modifier(@PathVariable Integer id,@RequestBody Avion avion){
        return avionService.misAJour(avion, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        avionService.supprimer(id);
    }
}
