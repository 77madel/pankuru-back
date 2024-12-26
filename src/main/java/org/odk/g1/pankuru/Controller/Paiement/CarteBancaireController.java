package org.odk.g1.pankuru.Controller.Paiement;

import org.odk.g1.pankuru.Entity.Paiement.CarteBancaire;
import org.odk.g1.pankuru.Service.Service.Paiement.CarteBancaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carteBancaire")
@CrossOrigin("http://localhost:4200")

public class CarteBancaireController {
    
    CarteBancaireService carteBancaireService;

    @PostMapping("/ajout")
    public ResponseEntity<CarteBancaire> ajouterCarteBancaire(@RequestBody CarteBancaire carteBancaire) {
        CarteBancaire savedCarteBancaire = carteBancaireService.ajout(carteBancaire);
        return ResponseEntity.ok(savedCarteBancaire);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerCarteBancaire(@PathVariable String id) {
        carteBancaireService.supprimer(id);
        return ResponseEntity.ok("Paiement supprimer avec succes !!!");
    }

    @GetMapping("/afficher")
    public List<CarteBancaire> listerPaiement() {
        return carteBancaireService.liste();
    }

    @GetMapping("/afficher/{id}")
    public ResponseEntity<CarteBancaire> afficherUneCarteBancaire(@PathVariable String id) {
        Optional<CarteBancaire> carteBancaire = carteBancaireService.trouverParId(id);

        return carteBancaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
