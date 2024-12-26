package org.odk.g1.pankuru.Service.Service.HumainService;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.Humain.Faq;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Repository.HumainRepo.FaqRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FaqService implements CrudService<Faq, Integer> {
    private final FaqRepo faqRepo;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    private final CompagnieService compagnieService;

    @Override
    public Faq ajout(Faq entity) {
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(userService.getCurrentUsernameId()) ;
        adminCompagnie.ifPresent(entity::setAdminCompagnie);

        return faqRepo.save(entity);
    }

    @Override
    public List<Faq> liste() {
        return compagnieService.getFaqByCompagnie();
    }

    @Override
    public Optional<Faq> trouverParId(Integer integer) {
        Optional<Faq> a = faqRepo.findById(integer);
        if(a.isPresent()){
            if(compagnieService.getFaqByCompagnie().contains(a.get())){
                return a;
            }
        }
        return Optional.empty();
    }

    @Override
    public Faq misAJour(Faq entity, Integer Id) {
        Optional<Faq> faq = faqRepo.findById(Id);
        if (faq.isPresent()) {
            if(compagnieService.getFaqByCompagnie().contains(faq.get())){
                Optional<Faq> faqExistant = faqRepo.findById(Id);
                if (faqExistant.isPresent()) {
                    Faq faqAModifier = faqExistant.get();
                    faqAModifier.setAdminCompagnie(entity.getAdminCompagnie());
                    faqAModifier.setReponse(entity.getReponse());
                    faqAModifier.setQuestionCategorie(entity.getQuestionCategorie());
                    return faqRepo.save(faqAModifier);
                } else {
                    throw new IllegalArgumentException("Le faq avec l'ID " + entity.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    @Override
    public void supprimer(Integer integer) {
        Optional<Faq> faq = faqRepo.findById(integer);
        if (faq.isPresent()) {
            if(compagnieService.getFaqByCompagnie().contains(faq.get())){
                faqRepo.deleteById(integer);
            }
        }
    }

}
