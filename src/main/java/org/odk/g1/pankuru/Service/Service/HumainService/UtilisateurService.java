package org.odk.g1.pankuru.Service.Service.HumainService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.Utilisateur;
import org.odk.g1.pankuru.Entity.Permission.Role;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.odk.g1.pankuru.Repository.HumainRepo.UtilisateurRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.odk.g1.pankuru.Utils.UtilService;
import org.odk.g1.pankuru.dto.SearchVolDTO;
import org.odk.g1.pankuru.dto.VolResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import javax.naming.directory.SearchResult;

@Service
@AllArgsConstructor
public class UtilisateurService implements CrudService<Utilisateur, Long>{

    private final UtilisateurRepo utilisateurRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Utilisateur ajout(Utilisateur entity) {

        if(!UtilService.isValidEmail(entity.getEmail())) {
            throw new RuntimeException("Votre mail est invalide");
        }

        Optional<Utilisateur> utilisateur = this.utilisateurRepo.findByEmail(entity.getEmail());
        if(utilisateur.isPresent()) {
            throw new RuntimeException("Votre mail est déjà utilisé");
        }

        String encodePassword = bCryptPasswordEncoder.encode(entity.getPassword());
        entity.setPassword(encodePassword);

        Role role = new Role();
        role.setId(2);
        role.setNom("USER");
        entity.setRole(role);
        System.out.println(role);
        return utilisateurRepo.save(entity);
    }

    @Override
    public List<Utilisateur> liste() {
        return utilisateurRepo.findAll();
    }

    /*public List<VolResult> searchvol(SearchVolDTO searchVolDTO){

    }*/

    public List<Map<String, Object>> liste1() {
        return utilisateurRepo.tout();
    }

    @Override
    public Optional<Utilisateur> trouverParId(Long id) {
        return utilisateurRepo.findById(id);
    }

    public List<Map<String, Object>> trouverParId1(Long id) {
        return utilisateurRepo.trouverParId(id);
    }

    @Override
    public Utilisateur misAJour(Utilisateur entity, Long Id) {
        Optional<Utilisateur> utilisateurExistant = utilisateurRepo.findById(Id);
        if (utilisateurExistant.isPresent()) {
            Utilisateur utilisateurAModifier = utilisateurExistant.get();
            utilisateurAModifier.setNom(entity.getNom());
            utilisateurAModifier.setPrenom(entity.getPrenom());
            utilisateurAModifier.setEmail(entity.getEmail());
            utilisateurAModifier.setPassword(entity.getPassword());
            utilisateurAModifier.setNumeroDeTelephone(entity.getNumeroDeTelephone());
            utilisateurAModifier.setPointDeFideliter(entity.getPointDeFideliter());
            utilisateurAModifier.setDateDeNaissance(entity.getDateDeNaissance());
            utilisateurAModifier.setNumeroDePassport(entity.getNumeroDePassport());
            utilisateurAModifier.setNumeroDeVisa(entity.getNumeroDeVisa());
            return utilisateurRepo.save(utilisateurAModifier);
        } else {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + entity.getId() + "n'existe pas.");
        }
    }

    @Override
    public void supprimer(Long id) {
        utilisateurRepo.deleteById(id);
    }

}
