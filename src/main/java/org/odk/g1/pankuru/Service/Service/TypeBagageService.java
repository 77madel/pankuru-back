package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.TypeBagage;
import org.odk.g1.pankuru.Repository.TypeBagageRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeBagageService implements CrudService<TypeBagage,Integer> {
    private TypeBagageRepository bagageRepository;
    @Override
    public TypeBagage ajout(TypeBagage typeBagage) {
        return bagageRepository.save(typeBagage);
    }

    @Override
    public List<TypeBagage> liste() {
        return bagageRepository.findAll();
    }

    @Override
    public Optional<TypeBagage> trouverParId(Integer id) {
        return bagageRepository.findById(id);
    }

    @Override
    public TypeBagage misAJour(TypeBagage typeBagage, Integer Id) {
        return bagageRepository.findById(Id)
                .map((t)->{
                    t.setBagage(t.getBagage());
                    t.setNom(t.getNom());
                    return bagageRepository.save(t);
                }).orElseThrow(() -> new RuntimeException("Type de bagage introuvable"));
    }

    @Override
    public void supprimer(Integer id) {
    bagageRepository.deleteById(id);
    }
}
