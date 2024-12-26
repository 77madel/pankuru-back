package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Passager;
import org.odk.g1.pankuru.Repository.PassagerRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@AllArgsConstructor
public class PassagerService implements CrudService<Passager,Long> {
    private PassagerRepository passagerRepository;
    @Override
    public Passager ajout(Passager passager) {
        return passagerRepository.save(passager);
    }

    @Override
    public List<Passager> liste() {
        return passagerRepository.findAll();
    }

    public List<Map<String, Object>> liste1(Long id) {
        return passagerRepository.tout(id);
    }

    @Override
    public Optional<Passager> trouverParId(Long id) {
        return passagerRepository.findById(id);
    }

    public List<Map<String, Object>> trouverParId1(Long id) {
        return passagerRepository.trouverParId(id);
    }

    @Override
    public Passager misAJour(Passager passager, Long Id) {
        return passagerRepository.findById(Id)
                .map((p)->{
                    p.setNom(passager.getNom());
                    p.setPrenom(passager.getPrenom());
                    p.setNumeroDeVisa(passager.getNumeroDeVisa());
                    p.setNumeroDePassPort(passager.getNumeroDePassPort());
                    p.setSiege(p.getSiege());

                    return passagerRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Passager introuvable"));
    }

    @Override
    public void supprimer(Long id) {
        passagerRepository.deleteById(id);
    }
}
