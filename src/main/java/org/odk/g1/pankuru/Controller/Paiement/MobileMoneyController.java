package org.odk.g1.pankuru.Controller.Paiement;

import org.odk.g1.pankuru.Entity.Paiement.MobileMoney;
import org.odk.g1.pankuru.Service.Service.Paiement.MobileMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/mobileMoney")
public class MobileMoneyController {
    @Autowired
    MobileMoneyService mobileMoneyService;

    @PostMapping("/ajout")
    public ResponseEntity<MobileMoney> ajouterMobileMoney(@RequestBody MobileMoney mobileMoney) {
        MobileMoney savedMobileMoney = mobileMoneyService.ajout(mobileMoney);
        return ResponseEntity.ok(savedMobileMoney);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerMobileMoney(@PathVariable String id) {
        mobileMoneyService.supprimer(id);
        return ResponseEntity.ok("Paiement supprimer avec succes !!!");
    }

    @GetMapping("/afficher")
    public List<MobileMoney> listerPaiement() {
        return mobileMoneyService.liste();
    }

    @GetMapping("/afficher/{id}")
    public ResponseEntity<MobileMoney> afficherUnMobileMoney(@PathVariable String id) {
        Optional<MobileMoney> mobileMoney = mobileMoneyService.trouverParId(id);
        return mobileMoney.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
