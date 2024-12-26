package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Siege;
import org.odk.g1.pankuru.Repository.SiegeRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SiegeService implements CrudService<Siege,Long> {
    private SiegeRepository siegeRepository;
    @Override
    public Siege ajout(Siege siege) {
        return siegeRepository.save(siege);
    }

    @Override
    public List<Siege> liste() {
        return siegeRepository.findAll();
    }

    @Override
    public Optional<Siege> trouverParId(Long id) {
        return siegeRepository.findById(id);
    }

    @Override
    public Siege misAJour(Siege siege, Long Id) {
        return siegeRepository.findById(Id)
                .map((s)->{
                    s.setDisponibilite(siege.getDisponibilite());
                    s.setNumero(siege.getNumero());
                    s.setPositionSiege(siege.getPositionSiege());

                    return siegeRepository.save(s);
                }).orElseThrow(()->new RuntimeException("Siege introuvable"));
    }

    @Override
    public void supprimer(Long id) {
        siegeRepository.deleteById(id);
    }
    // Méthode pour récupérer les sièges en fonction de l'ID de l'avion
    public List<Siege> getSiegesByAvionId(Long avionId) {
        return siegeRepository.findByAvionId(avionId);
    }
}
