package org.odk.g1.pankuru.Controller.Paiement;

import org.odk.g1.pankuru.Entity.Paiement.Paiement;
import org.odk.g1.pankuru.Service.Service.Paiement.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paiement")
@CrossOrigin("http://localhost:4200")

public class PaiementController {
    @Autowired
    PaiementService paiementService;
    @PostMapping("/ajoutPaiement")
    public ResponseEntity<Paiement> ajouterPaiement(@RequestBody Paiement paiement) {
        Paiement savedPaiement = paiementService.ajout(paiement);
        return ResponseEntity.ok(savedPaiement);
    }

    @DeleteMapping("/SuppPaiement/{id}")
    public ResponseEntity<String> supprimerPaiement(@PathVariable Long id) {
        paiementService.supprimer(id);
        return ResponseEntity.ok("Paiement supprimer avec succes !!!");
    }

    @GetMapping("/listePaiement")
    public List<Paiement> listerPaiement() {
        return paiementService.liste();
    }

    @GetMapping("/afficherUnPaiement/{id}")
    public ResponseEntity<Paiement> afficherUnPaiement(@PathVariable Long id) {
        Optional<Paiement> paiement = paiementService.trouverParId(id);
        return paiement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
