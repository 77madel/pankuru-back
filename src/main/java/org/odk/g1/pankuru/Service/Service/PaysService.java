package org.odk.g1.pankuru.Service.Service;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import org.odk.g1.pankuru.Entity.Localite.Pays;
import org.odk.g1.pankuru.Repository.PaysRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaysService implements CrudService<Pays, Integer>{

    private final PaysRepository paysRepository;
    private final CompagnieService compagnieService;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    @Override
    public Pays ajout(Pays pays) {
        Long adminCompagnieId = userService.getCurrentUsernameId();
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
        adminCompagnie.ifPresent(pays::setAdminCompagnie);
        return paysRepository.save(pays);
    }

    @Override
    public List<Pays> liste() {
        return paysRepository.findAll();
    }

    @Override
    public Optional<Pays> trouverParId(Integer id) {
        Optional<Pays> pays = paysRepository.findById(id);
        if (pays.isPresent()) {
            if(compagnieService.getPaysByCompagnieId().contains(pays.get())){
                return paysRepository.findById(id);
            }
        }
        return Optional.empty();
    }

    @Override
    public Pays misAJour(Pays pays, Integer Id) {
        Optional<Pays> pays1 = paysRepository.findById(Id);
        if (pays1.isPresent()) {
            if(compagnieService.getPaysByCompagnieId().contains(pays1.get())){
                Optional<Pays> paysExistant = paysRepository.findById(Id);
                if (paysExistant.isPresent()) {
                    Pays paysAModifier = paysExistant.get();
                    paysAModifier.setNom(pays.getNom());
                    paysAModifier.setPaysImageUrl(pays.getPaysImageUrl());

                    return paysRepository.save(paysAModifier);
                } else {
                    throw new IllegalArgumentException("Le pays avec l'ID " + pays.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    @Override
    public void supprimer(Integer id) {
        Optional<Pays> pays = paysRepository.findById(id);
        if (pays.isPresent()) {
            if(compagnieService.getPaysByCompagnieId().contains(pays.get())){
                paysRepository.deleteById(id);
            }
        }

    }
    
}
