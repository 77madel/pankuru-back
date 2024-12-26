package org.odk.g1.pankuru.Controller;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.TypeBagage;
import org.odk.g1.pankuru.Service.Service.TypeBagageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/typeBagage")
@CrossOrigin("http://localhost:4200")

public class TypeBagageControleur {
    private TypeBagageService typeBagageService;

    @PostMapping("/ajout")
    public TypeBagage ajouter(@RequestBody TypeBagage typeBagage){
        return typeBagageService.ajout(typeBagage);
    }
    @GetMapping("/afficher")
    public List<TypeBagage>lire(){
        return typeBagageService.liste();
    }
    @GetMapping("/afficher/{id}")
    public Optional<TypeBagage> parId(@PathVariable Integer id){
        return typeBagageService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public TypeBagage modif(@PathVariable Integer id,@RequestBody TypeBagage typeBagage){
        return typeBagageService.misAJour(typeBagage, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supp(@PathVariable Integer id){
        typeBagageService.supprimer(id);
    }

}
