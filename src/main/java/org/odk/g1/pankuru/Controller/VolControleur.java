package org.odk.g1.pankuru.Controller;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.VolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/vol")
@CrossOrigin("http://localhost:4200")
public class VolControleur {
    private VolService volService;
    private CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Vol ajout(@RequestBody Vol vol){
        return volService.ajout(vol);
    }
    @GetMapping("/afficher")
    public List<Vol>lire(){
        return compagnieService.getVolByCompagnie();
    }

    @GetMapping("/afficher/tout")
    public List<Vol>lireTout(){
        return volService.liste();
    }

    @GetMapping("/afficher/tout/{id}")
    public Optional<Vol> lireId(@PathVariable Long id){
        return volService.trouverParId(id);
    }

    @GetMapping("/afficher/{id}")
    public Optional<Vol> parId(@PathVariable Long id){
        return volService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Vol modif(@PathVariable Long id,@RequestBody Vol vol){
        return volService.misAJour(vol, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supp(@PathVariable Long id){
        volService.supprimer(id);
    }
}
