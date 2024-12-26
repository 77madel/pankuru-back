package org.odk.g1.pankuru.Service.Service;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Compagnie.Avion;
import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Repository.AvionRepository;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvionService implements CrudService<Avion, Integer>{

    private final AvionRepository avionRepository;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    private final CompagnieService compagnieService;

    @Override
    public Avion ajout(Avion avion) {
        Long adminCompagnieId = userService.getCurrentUsernameId();
        Optional<AdminCompagnie> adminCompagnie1 = adminCompagnieRepo.findById(adminCompagnieId);
        adminCompagnie1.ifPresent(avion::setAdminCompagnie);

        return avionRepository.save(avion);
    }

    @Override
    public List<Avion> liste() {
        return avionRepository.findAll();
    }

    @Override
    public Optional<Avion> trouverParId(Integer id) {
        Optional<Avion> avion = avionRepository.findById(id);
        if (avion.isPresent()) {
            if(compagnieService.getAvionByCompagnie().contains(avion.get())){
                return avionRepository.findById(id);
            }
        }
        return Optional.empty();
    }

    @Override
    public Avion misAJour(Avion avion, Integer Id) {
        Optional<Avion> avion1 = avionRepository.findById(Id);
        if (avion1.isPresent()) {
            if(compagnieService.getAvionByCompagnie().contains(avion1.get())){
                Optional<Avion> avionExistant = avionRepository.findById(Id);
                if (avionExistant.isPresent()) {
                    Avion avionAModifier = avionExistant.get();
                    avionAModifier.setMatricule(avion.getMatricule());
                    avionAModifier.setCapaciteTotale(avion.getCapaciteTotale());
                    avionAModifier.setNom(avion.getNom());
                    avionAModifier.setStatut(avion.getStatut());

                    return avionRepository.save(avionAModifier);
                } else {
                    throw new IllegalArgumentException("L'avion avec l'ID " + avion.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    @Override
    public void supprimer(Integer id) {
        Optional<Avion> avion = avionRepository.findById(id);
        if (avion.isPresent()) {
            if(compagnieService.getAvionByCompagnie().contains(avion.get())){
                avionRepository.deleteById(id);
            }
        }
    }

    
}
