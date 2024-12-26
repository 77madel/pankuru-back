package org.odk.g1.pankuru.Controller;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Passager;
import org.odk.g1.pankuru.Service.Service.PassagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/passager")
@CrossOrigin("http://localhost:4200")

public class PassagerControleur {
    private PassagerService passagerService;
    @PostMapping("/ajout")
    public Passager ajouter(@RequestBody Passager passager){
        return passagerService.ajout(passager);
    }
    @GetMapping("/afficher")
    public List<Passager>lire(){
        return passagerService.liste();
    }
    @GetMapping("/afficher/vol/{id}")
    public List<Map<String, Object>> passagerDUnVol(@PathVariable Long id){
        return passagerService.liste1(id);
    }
    @GetMapping("/afficher/{id}")
    public Optional<Passager> parId(@PathVariable Long id){
        return passagerService.trouverParId(id);
    }
    @PutMapping("/modifier/{id}")
    public Passager modif(@PathVariable Long id,@RequestBody Passager passager){
        return passagerService.misAJour(passager, id);
    }
    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id){
        passagerService.supprimer(id);
    }

}
