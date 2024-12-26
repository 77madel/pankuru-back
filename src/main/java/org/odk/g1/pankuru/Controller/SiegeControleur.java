package org.odk.g1.pankuru.Controller;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Siege;
import org.odk.g1.pankuru.Service.Service.SiegeDTO.SiegeDTOService;
import org.odk.g1.pankuru.Service.Service.SiegeService;
import org.odk.g1.pankuru.dto.SiegeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/siege")
@CrossOrigin("http://localhost:4200")

public class SiegeControleur {
    private SiegeService siegeService;
    private SiegeDTOService siegeDTOService;

    @PostMapping("/ajout")
    public Siege ajouter(@RequestBody SiegeDTO siege){
        return siegeDTOService.creerSiege(siege);
    }
    @GetMapping("/afficher")
    public List<Siege>lire(){
        return siegeService.liste();
    }

    @GetMapping("/afficher/{id}")
    public Optional<Siege> parId(@PathVariable Long id){
        return siegeService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Siege modi(@PathVariable Long id,@RequestBody Siege siege){
        return siegeService.misAJour(siege, id);
    }
    @DeleteMapping("/supprimer/{id}")
    public void supp(@PathVariable Long id){
        siegeService.supprimer(id);
    }
    // Endpoint pour récupérer les sièges en fonction de l'ID de l'avion
    @GetMapping("/afficher/avion/{avionId}")
    public List<Siege> getSiegesByAvionId(@PathVariable Long avionId) {
        return siegeService.getSiegesByAvionId(avionId);
    }
}
