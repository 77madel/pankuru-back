package org.odk.g1.pankuru.Service.Service;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.ReservationDeVol.ClasseSiege;
import org.odk.g1.pankuru.Repository.ClasseSiegeRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ClasseSiegeService implements CrudService<ClasseSiege,Integer> {
     private  ClasseSiegeRepository classeSiegeRepository;
    @Override
    public ClasseSiege ajout(ClasseSiege classeSiege) {
        return classeSiegeRepository.save(classeSiege);
    }

    @Override
    public List<ClasseSiege> liste() {
        return classeSiegeRepository.findAll();
    }

    @Override
    public Optional<ClasseSiege> trouverParId(Integer id) {
        return classeSiegeRepository.findById(id);
    }

    @Override
    public ClasseSiege misAJour(ClasseSiege classeSiege, Integer Id) {
        return classeSiegeRepository.findById(Id)
                .map((c)->{
                    c.setNom(classeSiege.getNom());
                    c.setPositionSiege(classeSiege.getPositionSiege());
                    return classeSiegeRepository.save(c);
                }).orElseThrow(()-> new RuntimeException("Classe introuvable"));
    }

    @Override
    public void supprimer(Integer id) {
        classeSiegeRepository.deleteById(id);
    }
}
