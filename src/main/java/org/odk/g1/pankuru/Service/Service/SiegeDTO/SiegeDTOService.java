package org.odk.g1.pankuru.Service.Service.SiegeDTO;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Enum.SiegeDisponible;
import org.odk.g1.pankuru.Entity.ReservationDeVol.ClasseSiege;
import org.odk.g1.pankuru.Entity.ReservationDeVol.PositionSiege;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Siege;
import org.odk.g1.pankuru.Repository.ClasseSiegeRepository;
import org.odk.g1.pankuru.Repository.PositionSiegeRepository;
import org.odk.g1.pankuru.Repository.SiegeRepository;
import org.odk.g1.pankuru.dto.SiegeDTO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SiegeDTOService {
    private final SiegeRepository siegeRepository;
    private final ClasseSiegeRepository classeSiegeRepository;
    private final PositionSiegeRepository positionSiegeRepository;

    public Siege creerSiege(SiegeDTO siegeDTO) {
        ClasseSiege classeSiege = classeSiegeRepository.findByNom(siegeDTO.getClasseSiege());
        if (classeSiege == null) {
            throw new RuntimeException("La classe du siege n'existe pas");
        }
        PositionSiege p = new PositionSiege();
        p.setClasseSiege(classeSiege);
        p.setNom(siegeDTO.getPositionSiege());
        p.setTarif(siegeDTO.getTarif());

        PositionSiege positionSiege = positionSiegeRepository.save(p);

        Siege siege = new Siege();
        siege.setNumero(siegeDTO.getNumeroSiege());
        siege.setPositionSiege(positionSiege);
        siege.setAvion(siegeDTO.getAvion());
        siege.setDisponibilite(SiegeDisponible.OUI);

        return siegeRepository.save(siege);


    }
}
