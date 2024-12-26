package org.odk.g1.pankuru.Controller.HumainController;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.Faq;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.HumainService.FaqService;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/faq")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class FaqController {

    private final FaqService faqService;
    private final CompagnieService compagnieService;

    @PostMapping("/ajout")
    public Faq ajouter(@RequestBody Faq faq){
        return faqService.ajout(faq);
    }

    @GetMapping("/afficher")
    public List<Faq> afficher(){
        return compagnieService.getFaqByCompagnie();
    }
    @GetMapping("/afficher/{id}")
    public Optional<Faq> trouverParId(@PathVariable Integer id){
        return faqService.trouverParId(id);
    }

    @PutMapping("/modifier/{id}")
    public Faq modifier(@PathVariable Integer id,@RequestBody Faq faq){
        return faqService.misAJour(faq, id);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Integer id){
        faqService.supprimer(id);
    }
}
