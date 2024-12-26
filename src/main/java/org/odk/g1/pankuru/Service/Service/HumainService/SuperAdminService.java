package org.odk.g1.pankuru.Service.Service.HumainService;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.SuperAdmin;
import org.odk.g1.pankuru.Entity.Permission.Role;
import org.odk.g1.pankuru.Repository.HumainRepo.SuperAdminRepo;
import org.odk.g1.pankuru.Repository.RoleRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.odk.g1.pankuru.Utils.UtilService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuperAdminService implements CrudService<SuperAdmin, Long>{

    private final SuperAdminRepo superAdminRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public SuperAdmin ajout(SuperAdmin entity) {

        if(!UtilService.isValidEmail(entity.getEmail())) {
            throw  new RuntimeException("Votre mail est invalide");
        }

        Optional<SuperAdmin> superAdmin = this.superAdminRepo.findByEmail(entity.getEmail());
        if(superAdmin.isPresent()) {
            throw  new RuntimeException("Votre mail est déjà utilisé");
        }

        String encodePassword = bCryptPasswordEncoder.encode(entity.getPassword());
        entity.setPassword(encodePassword);
        Optional<Role> role = roleRepository.findByNom("ADMIN");
        role.ifPresent(entity::setRole);

        return superAdminRepo.save(entity);
    }

    @Override
    public List<SuperAdmin> liste() {
        return superAdminRepo.findAll();
    }

    @Override
    public Optional<SuperAdmin> trouverParId(Long id) {
        return superAdminRepo.findById(id);
    }

    @Override
    public SuperAdmin misAJour(SuperAdmin superAdmin, Long Id) {
        Optional<SuperAdmin> superAdminExistant = superAdminRepo.findById(Id);
        if (superAdminExistant.isPresent()) {
            SuperAdmin superAdminAModifier = superAdminExistant.get();
            superAdminAModifier.setNom(superAdmin.getNom());
            superAdminAModifier.setPrenom(superAdmin.getPrenom());
            superAdminAModifier.setEmail(superAdmin.getEmail());
            superAdminAModifier.setPassword(superAdmin.getPassword());
            superAdminAModifier.setNumeroDeTelephone(superAdmin.getNumeroDeTelephone());
            superAdminAModifier.setPseudo(superAdmin.getPseudo());
            return superAdminRepo.save(superAdminAModifier);
        } else {
            throw new IllegalArgumentException("Le super admin avec l'ID " + superAdmin.getId() + "n'existe pas.");
        }
    }

    @Override
    public void supprimer(Long id) {
        superAdminRepo.deleteById(id);
    }

}
