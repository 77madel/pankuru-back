package org.odk.g1.pankuru.Service.Service.HumainService;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.Humain.Rapport;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Repository.HumainRepo.RapportRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.UserService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RapportService implements CrudService<Rapport, Long>{
    
    private final RapportRepo rapportRepo;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    private final CompagnieService compagnieService;
    
    @Override
    public Rapport ajout(Rapport entity) {
        Long adminCompagnieId = userService.getCurrentUsernameId();
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
        adminCompagnie.ifPresent(entity::setAdminCompagnie);

        return rapportRepo.save(entity);
    }

    @Override
    public List<Rapport> liste() {
        return rapportRepo.findAll();
    }

    @Override
    public Optional<Rapport> trouverParId(Long id) {
        Optional<Rapport> rapport = rapportRepo.findById(id);
        if (rapport.isPresent()) {
            if(compagnieService.getRapportByCompagnie().contains(rapport.get())){
                return rapportRepo.findById(id);
            }
        }
        return Optional.empty();
    }

    @Override
    public Rapport misAJour(Rapport entity, Long Id) {
        Optional<Rapport> rapport = rapportRepo.findById(Id);
        if (rapport.isPresent()) {
            if(compagnieService.getRapportByCompagnie().contains(rapport.get())){
                Optional<Rapport> rapportExistant = rapportRepo.findById(Id);
                if (rapportExistant.isPresent()) {
                    Rapport rapportAModifier = rapportExistant.get();
                    rapportAModifier.setTitre(entity.getTitre());
                    rapportAModifier.setDateCreation(entity.getDateCreation());
                    rapportAModifier.setType(entity.getType());
                    rapportAModifier.setUrl(entity.getUrl());
                    rapportAModifier.setCommentaires(entity.getCommentaires());

                    Long adminCompagnieId = userService.getCurrentUsernameId();
                    Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
                    adminCompagnie.ifPresent(entity::setAdminCompagnie);

                    return rapportRepo.save(rapportAModifier);
                } else {
                    throw new IllegalArgumentException("Le rapport avec l'ID " + entity.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    @Override
    public void supprimer(Long id) {
        Optional<Rapport> rapport = rapportRepo.findById(id);
        if (rapport.isPresent()) {
            if(compagnieService.getRapportByCompagnie().contains(rapport.get())){
                rapportRepo.deleteById(id);
            }
        }
    }
    
}
