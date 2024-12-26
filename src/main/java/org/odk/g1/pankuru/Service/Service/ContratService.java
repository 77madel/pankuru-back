package org.odk.g1.pankuru.Service.Service;

import java.util.List;
import java.util.Optional;

import org.odk.g1.pankuru.Entity.Compagnie.Contrat;
import org.odk.g1.pankuru.Repository.ContratRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContratService implements CrudService<Contrat, Long>{

    private final ContratRepository contratRepository;
    @Override
    public Contrat ajout(Contrat contrat) {
        return contratRepository.save(contrat); 
    }

    @Override
    public List<Contrat> liste() {
        return contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> trouverParId(Long id) {
        return contratRepository.findById(id);
    }

    @Override
    public Contrat misAJour(Contrat contrat, Long Id) {
        // Vérifiez si le contrat existe en base
        Optional<Contrat> contratExistant = contratRepository.findById(Id);
        if (contratExistant.isPresent()) {
            Contrat contratAModifier = contratExistant.get();
            contratAModifier.setType(contrat.getType());
            contratAModifier.setDescription(contrat.getDescription());
            contratAModifier.setDateDebut(contrat.getDateDebut());
            contratAModifier.setDateFin(contrat.getDateFin());
            contratAModifier.setStatut(contrat.getStatut());
            contratAModifier.setCgu(contrat.getCgu());
            contratAModifier.setCommentaires(contrat.getCommentaires());
            return contratRepository.save(contratAModifier);
        } else {
            // Le contrat n'existe pas, vous pouvez lancer une exception ou gérer ce cas comme vous le souhaitez
            throw new IllegalArgumentException("Le contrat avec l'ID " + contrat.getId() + " n'existe pas.");
        }
    }

    @Override
    public void supprimer(Long id) {
        contratRepository.deleteById(id);
    }
    
}
