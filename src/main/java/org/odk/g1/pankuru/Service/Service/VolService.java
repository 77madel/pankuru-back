package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Humain.AdminCompagnie;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Repository.VolRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VolService implements CrudService<Vol,Long> {
    private VolRepository volRepository;
    private UserService userService;
    private AdminCompagnieRepo adminCompagnieRepo;
    private CompagnieService compagnieService;

    @Override
    public Vol ajout(Vol vol) {
        Long adminCompagnieId = userService.getCurrentUsernameId();
        Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
        adminCompagnie.ifPresent(vol::setAdminCompagnie);
        return volRepository.save(vol);
    }

    @Override
    public List<Vol> liste() {
        return volRepository.findAll();
    }

    @Override
    public Optional<Vol> trouverParId(Long id) {
        return volRepository.findById(id);
    }

    @Override
    public Vol misAJour(Vol vol, Long Id) {
        Optional<Vol> vol1 = volRepository.findById(Id);
        if (vol1.isPresent()) {
            if(compagnieService.getVolByCompagnie().contains(vol1.get())){
                return volRepository.findById(Id)
                        .map((v)->{
                            v.setReservationList(vol.getReservationList());
                            v.setSatut(v.getSatut());
                            v.setAeroportDepart(vol.getAeroportDepart());
                            v.setDateEtHeureDepart(vol.getDateEtHeureDepart());
                            v.setDateEtHeureArrivee(vol.getDateEtHeureArrivee());
                            v.setNumeroDeVol(vol.getNumeroDeVol());

                            Long adminCompagnieId = userService.getCurrentUsernameId();
                            Optional<AdminCompagnie> adminCompagnie = adminCompagnieRepo.findById(adminCompagnieId);
                            adminCompagnie.ifPresent(v::setAdminCompagnie);

                            return volRepository.save(v);
                        }).orElseThrow(()->new RuntimeException("Vol introuvable"));
            }
        }
        return null;
    }

    @Override
    public void supprimer(Long id) {
        Optional<Vol> vol = volRepository.findById(id);
        if (vol.isPresent()) {
            if(compagnieService.getVolByCompagnie().contains(vol.get())){
                volRepository.deleteById(id);
            }
        }

    }
}
