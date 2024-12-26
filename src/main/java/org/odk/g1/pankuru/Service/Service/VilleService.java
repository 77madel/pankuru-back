package org.odk.g1.pankuru.Service.Service;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.Localite.Ville;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Repository.VilleRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VilleService implements CrudService<Ville, Integer>{
    private final VilleRepository villeRepository;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    private final CompagnieService compagnieService;

    @Override
    public Ville ajout(Ville ville) {
        Long adminCompagnieId = userService.getCurrentUsernameId();
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
        adminCompagnie.ifPresent(ville::setAdminCompagnie);
        return villeRepository.save(ville);
    }

    @Override
    public List<Ville> liste() {
        return villeRepository.findAll();
    }


    @Override
    public Optional<Ville> trouverParId(Integer id) {
        return villeRepository.findById(id);
    }

    @Override
    public Ville misAJour(Ville ville, Integer Id) {
        Optional<Ville> ville1 = villeRepository.findById(Id);
        if (ville1.isPresent()) {
            if(compagnieService.getVilleByCompagnie().contains(ville1.get())){
                Optional<Ville> villeExistant = villeRepository.findById(Id);
                if (villeExistant.isPresent()) {
                    Ville villeAModifier = villeExistant.get();
                    villeAModifier.setNom(ville.getNom());

                    return villeRepository.save(villeAModifier);
                } else {
                    throw new IllegalArgumentException("La ville avec l'ID " + ville.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    @Override
    public void supprimer(Integer id) {
        Optional<Ville> ville = villeRepository.findById(id);
        if (ville.isPresent()) {
            if(compagnieService.getVilleByCompagnie().contains(ville.get())){
                villeRepository.deleteById(id);
            }
        }
    }
}
