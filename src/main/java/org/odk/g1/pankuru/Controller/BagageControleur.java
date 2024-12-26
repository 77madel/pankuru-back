package org.odk.g1.pankuru.Controller;

import org.odk.g1.pankuru.Entity.ReservationDeVol.Bagage;
import org.odk.g1.pankuru.Service.Service.BagageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bagage")
@CrossOrigin("http://localhost:4200")

public class BagageControleur {
    private BagageService bagageService;
    @PostMapping("/ajout")
    public Bagage ajoutBagage( Bagage bagage){
        return bagageService.ajout(bagage);
    }
    @GetMapping("/afficher")
    public List<Bagage> ListBagage(){
        return bagageService.liste();
    }
    @GetMapping("/afficher/{id}")
    public Optional<Bagage> listParId(@PathVariable Long id){
        return bagageService.trouverParId(id);
    }
    @PutMapping("/modifier/{id}")
    public Bagage modif(@PathVariable Long id,Bagage bagage){
        return bagageService.misAJour(bagage, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerBagage(@PathVariable Long id){
    bagageService.supprimer(id);


}
}
