package org.odk.g1.pankuru.Service.Service.Paiement;
// import org.odk.g1.pankuru.Entity.Paiement.CarteBancaire;
// import org.odk.g1.pankuru.Entity.Paiement.MobileMoney;
import org.odk.g1.pankuru.Entity.Paiement.Paiement;
import org.odk.g1.pankuru.Repository.Paiement.PaiementRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService implements CrudService<Paiement, Long>{

    @Autowired
    PaiementRepository paiementRepository;
    @Override
    public Paiement ajout(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public List<Paiement> liste() {
        return paiementRepository.findAll();
    }

    @Override
    public Optional<Paiement> trouverParId(Long paiementID) {
        return paiementRepository.findById(paiementID);
    }

    @Override
    public Paiement misAJour(Paiement newInfoPaiement, Long Id) {
        Paiement paiementBD = paiementRepository.findById(Id).
                orElseThrow(() -> new RuntimeException("paiement non trouvé"));
        paiementBD.setMontant(newInfoPaiement.getMontant());
        paiementBD.setDatePaiement(newInfoPaiement.getDatePaiement());
        paiementBD.setReservation(newInfoPaiement.getReservation());
        return paiementBD;
    }
    
    @Override
    public void supprimer(Long paiementId) {
        paiementRepository.deleteById(paiementId);
    }
}
