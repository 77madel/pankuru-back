package org.odk.g1.pankuru.Service.Service.HumainService;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.Humain.Personnel;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Repository.HumainRepo.PersonnelRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.odk.g1.pankuru.Service.Service.CompagnieService;
import org.odk.g1.pankuru.Service.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PersonnelService implements CrudService<Personnel, Long>{

    private final PersonnelRepo personnelRepo;
    private final UserService userService;
    private final AdminCompagnieRepo adminCompagnieRepo;
    private final CompagnieService compagnieService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Personnel ajout(Personnel entity) {
        Long adminCompagnieId = userService.getCurrentUsernameId();
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
        adminCompagnie.ifPresent(entity::setAdminCompagnie);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return personnelRepo.save(entity);
    }

    @Override
    public List<Personnel> liste() {
        return personnelRepo.findAll();
    }

    @Override
    public Optional<Personnel> trouverParId(Long id) {
        Optional<Personnel> personnel = personnelRepo.findById(id);
        if (personnel.isPresent()) {
            if(compagnieService.getPersonnelByCompagnie().contains(personnel.get())){
                return personnelRepo.findById(id);
            }
        }
        return Optional.empty();
    }

    @Override
    public Personnel misAJour(Personnel entity, Long Id) {
        Optional<Personnel> personnel = personnelRepo.findById(Id);
        if (personnel.isPresent()) {
            if(compagnieService.getPersonnelByCompagnie().contains(personnel.get())){
                Optional<Personnel> personnelExistant = personnelRepo.findById(Id);
                if (personnelExistant.isPresent()) {
                    Personnel personnelAModifier = modifierPersonnelExistant(entity, personnelExistant.get());

                    Long adminCompagnieId = userService.getCurrentUsernameId();
                    Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
                    adminCompagnie.ifPresent(entity::setAdminCompagnie);

                    return personnelRepo.save(personnelAModifier);
                } else {
                    throw new IllegalArgumentException("Le personnel avec l'ID " + entity.getId() + "n'existe pas.");
                }
            }
        }
        return null;
    }

    private static Personnel modifierPersonnelExistant(Personnel entity, Personnel personnelExistant) {
        personnelExistant.setNom(entity.getNom());
        personnelExistant.setPrenom(entity.getPrenom());
        personnelExistant.setEmail(entity.getEmail());
        personnelExistant.setPassword(entity.getPassword());
        personnelExistant.setNumeroDeTelephone(entity.getNumeroDeTelephone());
        personnelExistant.setDateEmbauche(entity.getDateEmbauche());
        personnelExistant.setPoste(entity.getPoste());
        personnelExistant.setEnService(entity.isEnService());
        return personnelExistant;
    }


    @Override
    public void supprimer(Long id) {
        Personnel personnelExistant = personnelRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Le personnel avec l'ID " + id + "n'existe pas."));
        if(compagnieService.getPersonnelByCompagnie().contains(personnelExistant)){
            personnelRepo.delete(personnelExistant);
        }
    }

}
