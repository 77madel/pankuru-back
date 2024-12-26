package org.odk.g1.pankuru.Controller;

import lombok.AllArgsConstructor;
// import org.odk.g1.pankuru.Entity.ReservationDeVol.Bagage;
import org.odk.g1.pankuru.Entity.ReservationDeVol.ClasseSiege;
import org.odk.g1.pankuru.Service.Service.ClasseSiegeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Classe")
@CrossOrigin("http://localhost:4200")

public class ClasseSiegeControleur {
    private ClasseSiegeService classeSiegeService;
    @PostMapping("/ajout")
    public ClasseSiege supprimerClasse(@RequestBody ClasseSiege classeSiege){
        return classeSiegeService.ajout(classeSiege);
    }

    @GetMapping("/afficher")
    public List<ClasseSiege> lire(){
        return classeSiegeService.liste();
    }

    @GetMapping("/afficher/{id}")
    public Optional<ClasseSiege> parId(@PathVariable Integer id){
        return classeSiegeService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public ClasseSiege modif(@PathVariable Integer id,ClasseSiege classeSiege ){
        return classeSiegeService.misAJour(classeSiege, id);
    }
    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        classeSiegeService.supprimer(id);
    }

}
