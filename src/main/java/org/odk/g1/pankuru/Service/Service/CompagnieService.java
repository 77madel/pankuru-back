package org.odk.g1.pankuru.Service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.odk.g1.pankuru.Entity.Compagnie.Avion;
import org.odk.g1.pankuru.Entity.Compagnie.Compagnie;
import org.odk.g1.pankuru.Entity.Humain.*;
import org.odk.g1.pankuru.Entity.Localite.Aeroport;
import org.odk.g1.pankuru.Entity.Localite.Pays;
import org.odk.g1.pankuru.Entity.Localite.Ville;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.odk.g1.pankuru.Repository.*;
import org.odk.g1.pankuru.Repository.HumainRepo.PersonnelRepo;
import org.odk.g1.pankuru.Repository.HumainRepo.RapportRepo;
import org.odk.g1.pankuru.Repository.HumainRepo.SuperAdminRepo;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CompagnieService implements CrudService<Compagnie, Integer>{

    private final CompagnieRepository compagnieRepository;
    private UserService userService;
    private AeroportRepository aeroportRepository;
    private PaysRepository paysRepository;
    private VilleRepository villeRepository;
    private AvionRepository avionRepository;
    private final SuperAdminRepo superAdminRepo;
    private PersonnelRepo personnelRepo;
    private VolRepository volRepository;
    private FaqRepository faqRepository;
    private RapportRepo rapportRepo;

    public List<Aeroport> getAeroportsByCompagnieId() {
        Integer compagnieId = userService.getCompagnieId();

        List<Aeroport> aeroports = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                aeroports.addAll(aeroportRepository.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return aeroports;
    }

    public List<Pays> getPaysByCompagnieId() {
        Integer compagnieId = userService.getCompagnieId();

        List<Pays> pays = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                pays.addAll(paysRepository.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return pays;
    }

    public List<Ville> getVilleByCompagnie() {
        Integer compagnieId = userService.getCompagnieId();

        List<Ville> villes = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                villes.addAll(villeRepository.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return villes;
    }

    public List<Avion> getAvionByCompagnie() {
        Integer compagnieId = userService.getCompagnieId();

        List<Avion> avions = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                avions.addAll(avionRepository.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return avions;
    }

    public List<Personnel> getPersonnelByCompagnie() {
        Integer compagnieId = userService.getCompagnieId();

        List<Personnel> personnels = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                personnels.addAll(personnelRepo.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return personnels;
    }

    public List<Faq> getFaqByCompagnie() {
        Integer compagnieId = userService.getCompagnieId();

        List<Faq> faqs = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                faqs.addAll(faqRepository.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return faqs;
    }

    public List<Vol> getVolByCompagnie() {
        Integer compagnieId = userService.getCompagnieId();

        List<Vol> vols = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                vols.addAll(volRepository.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return vols;
    }

    public List<Rapport> getRapportByCompagnie() {
        Integer compagnieId = userService.getCompagnieId();

        List<Rapport> rapports = new ArrayList<>();
        Compagnie compagnie = compagnieRepository.findById(compagnieId).orElse(null);

        if (compagnie != null) {
            for (AdminCompagnie adminCompagnie : compagnie.getAdminCompagnieList()) {
                rapports.addAll(rapportRepo.findByAdminCompagnieId(adminCompagnie.getId()));
            }
        }
        return rapports;
    }


    @Override
    public Compagnie ajout(Compagnie compagnie) {
        Long superAdminId = userService.getCurrentUsernameId();
        Optional<SuperAdmin> superAdmin = superAdminRepo.findById(superAdminId);
        superAdmin.ifPresent(compagnie::setSuperAdmin);
        return compagnieRepository.save(compagnie);
    }

    @Override
    public List<Compagnie> liste() {
        return compagnieRepository.findAll();
    }

    @Override
    public Optional<Compagnie> trouverParId(Integer id) {
        return compagnieRepository.findById(id);
    }

    @Override
    public Compagnie misAJour(Compagnie compagnie, Integer Id) {
        if (Id == null) {
            throw new IllegalArgumentException("L'identifiant de la compagnie ne doit pas être nul.");
        }
        Optional<Compagnie> compagnieExistant = compagnieRepository.findById(Id);
        if (compagnieExistant.isPresent()) {
            Compagnie compagnieAModifier = modifierCompagnieExistant(compagnie, compagnieExistant.get());

            Long superAdminId = userService.getCurrentUsernameId();
            Optional<SuperAdmin> superAdmin = superAdminRepo.findById(superAdminId);
            superAdmin.ifPresent(compagnieAModifier::setSuperAdmin);

            return compagnieRepository.save(compagnieAModifier);
        } else {
            throw new IllegalArgumentException("La compagnie avec l'ID " + compagnie.getId() + " n'existe pas.");
        }
    }

    private static Compagnie modifierCompagnieExistant(Compagnie compagnie, Compagnie compagnieExistant) {
        compagnieExistant.setMatricule(compagnie.getMatricule());
        compagnieExistant.setNom(compagnie.getNom());
        compagnieExistant.setLogoUrl(compagnie.getLogoUrl());
        compagnieExistant.setCodeIATA(compagnie.getCodeIATA());
        compagnieExistant.setCodeICAO(compagnie.getCodeICAO());
        compagnieExistant.setNumeroTelephone(compagnie.getNumeroTelephone());
        compagnieExistant.setEmail(compagnie.getEmail());
        compagnieExistant.setSiteWeb(compagnie.getSiteWeb());
        compagnieExistant.setNumeroLicence(compagnie.getNumeroLicence());
        return compagnieExistant;
    }

    @Override
    public void supprimer(Integer id) {
        Optional<Compagnie> compagnieExistant = compagnieRepository.findById(id);
        if (compagnieExistant.isPresent()){
            Compagnie compagnie = compagnieExistant.get();
            compagnie.setLocked(!compagnie.isLocked());
        }
    }
    
}
