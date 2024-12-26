package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.PositionSiege;
import org.odk.g1.pankuru.Repository.PositionSiegeRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PositionSiegeService implements CrudService<PositionSiege,Integer> {
    private PositionSiegeRepository positionSiegeRepository;
    @Override
    public PositionSiege ajout(PositionSiege position) {
        return positionSiegeRepository.save(position);
    }

    @Override
    public List<PositionSiege> liste() {
        return positionSiegeRepository.findAll();
    }

    @Override
    public Optional<PositionSiege> trouverParId(Integer id) {
        return positionSiegeRepository.findById(id);
    }

    @Override
    public PositionSiege misAJour(PositionSiege positionSiege, Integer Id) {
        return positionSiegeRepository.findById(Id)
                .map((p)->{
                    p.setSiege(positionSiege.getSiege());
                    p.setNom(positionSiege.getNom());
                    return positionSiegeRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Position introuvable"));
    }

    @Override
    public void supprimer(Integer id) {
        positionSiegeRepository.deleteById(id);
    }
}
