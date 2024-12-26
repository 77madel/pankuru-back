package org.odk.g1.pankuru.Service.Service;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.Localite.Aeroport;
import org.odk.g1.pankuru.Repository.AeroportRepository;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AeroportService implements CrudService<Aeroport, Long>{

    private final AeroportRepository aeroportRepository;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    private final CompagnieService compagnieService;

    @Override
    public Aeroport ajout(Aeroport aeroport) {
        Long idadminCompagnie = userService.getCurrentUsernameId();
        System.out.println(idadminCompagnie);
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(idadminCompagnie);
        adminCompagnie.ifPresent(aeroport::setAdminCompagnie);

        return aeroportRepository.save(aeroport);
    }

    @Override
    public List<Aeroport> liste() {
        return aeroportRepository.findAll();
    }

    @Override
    public Optional<Aeroport> trouverParId(Long id) {
        Optional<Aeroport> aeroport = aeroportRepository.findById(id);
        if (aeroport.isPresent()) {
            if(compagnieService.getAeroportsByCompagnieId().contains(aeroport.get())){
                return aeroportRepository.findById(id);
            }
        }
        return Optional.empty();
    }

    @Override
    public Aeroport misAJour(Aeroport aeroport, Long Id) {
        Optional<Aeroport> aeroport1 = aeroportRepository.findById(Id);
        if (aeroport1.isPresent()) {
            if(compagnieService.getAeroportsByCompagnieId().contains(aeroport1.get())){
                Optional<Aeroport> aeroportExistant = aeroportRepository.findById(Id);
                if (aeroportExistant.isPresent()) {
                    Aeroport aeroportAModifier = aeroportExistant.get();
                    aeroportAModifier.setNom(aeroport.getNom());
                    aeroportAModifier.setCodeIATA(aeroport.getCodeIATA());
                    aeroportAModifier.setLongitude(aeroport.getLongitude());
                    aeroportAModifier.setLatitude(aeroport.getLatitude());
                    aeroportAModifier.setAltitude(aeroport.getAltitude());
                    aeroportAModifier.setCapaciteParking(aeroport.getCapaciteParking());
                    aeroportAModifier.setNombreDePistes(aeroport.getNombreDePistes());

                    Long adminCompagnieId = userService.getCurrentUsernameId();
                    Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
                    adminCompagnie.ifPresent(aeroportAModifier::setAdminCompagnie);

                    return aeroportRepository.save(aeroportAModifier);
                } else {
                    throw new IllegalArgumentException("L'aeroport avec l'ID " + aeroport.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    @Override
    public void supprimer(Long id) {
        Optional<Aeroport> aeroport = aeroportRepository.findById(id);
        if (aeroport.isPresent()) {
            if(compagnieService.getAeroportsByCompagnieId().contains(aeroport.get())){
                aeroportRepository.deleteById(id);
            }
        }
    }
    
}
