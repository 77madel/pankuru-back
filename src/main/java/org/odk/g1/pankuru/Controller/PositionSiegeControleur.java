package org.odk.g1.pankuru.Controller;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.PositionSiege;
import org.odk.g1.pankuru.Service.Service.PositionSiegeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/position")
@CrossOrigin("http://localhost:4200")

public class PositionSiegeControleur {
    private PositionSiegeService positionSiegeService;
    @PostMapping("/ajout")
    public PositionSiege ajouter(@RequestBody PositionSiege positionSiege){
        return positionSiegeService.ajout(positionSiege);
    }
    @GetMapping("/afficher")
    public List<PositionSiege> lire(){
        return positionSiegeService.liste();


    }
    @GetMapping("/afficher/{id}")
    public Optional<PositionSiege> parId(@PathVariable Integer id){
        return positionSiegeService.trouverParId(id);
    }
    @PutMapping("/modifier/{id}")
    public PositionSiege modif(@PathVariable Integer id,@RequestBody PositionSiege positionSiege){
        return positionSiegeService.misAJour(positionSiege, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        positionSiegeService.supprimer(id);
    }

}
