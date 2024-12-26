package org.odk.g1.pankuru.Service.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Humain.Notification;
import org.odk.g1.pankuru.Entity.Humain.Utilisateur;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Reservation;
import org.odk.g1.pankuru.Entity.ReservationDeVol.Vol;
import org.odk.g1.pankuru.Repository.HumainRepo.UtilisateurRepo;
import org.odk.g1.pankuru.Repository.NotificationRepository;
import org.odk.g1.pankuru.Repository.ReservationRepository;
import org.odk.g1.pankuru.Repository.VolRepository;
import org.odk.g1.pankuru.Service.Interface.CrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ReservationService implements CrudService<Reservation,Long> {
    private ReservationRepository reservationRepository;
    private UserService userService;
    private VolRepository volRepository;
    private UtilisateurRepo utilisateurRepo;
    private NotificationRepository notificationRepository;


    @Override
    public Reservation ajout(Reservation reservation) {
        Long userId = userService.getCurrentUsernameId();

        Utilisateur u = new Utilisateur();
        u.setId(userId);

        reservation.setUtilisateur(u);

        Reservation reservationSaved = reservationRepository.save(reservation);

        Notification notification = new Notification();

        //Optional<Utilisateur> utilisateur = utilisateurRepo.findById(userId);
        Utilisateur utilisateur = utilisateurRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé pour l'ID : " + userId));

        notification.setUtilisateur(utilisateur);

        Long volId = reservationSaved.getVol().getId();
        Vol vol = volRepository.getReferenceById(volId);

        notification.setReservation(reservationSaved);
        notification.setDate(LocalDate.now());
        notification.setHeure(LocalTime.now());
        notification.setObjet("Reservation de vol");

        notification.setVol(vol);
        notificationRepository.save(notification);


        return reservationSaved;
    }

    @Override
    public List<Reservation> liste() {
        Long userId = userService.getCurrentUsernameId();
        return reservationRepository.findReservationByUtilisateurId(userId);
    }

    @Override
    public Optional<Reservation> trouverParId(Long id) {
        return reservationRepository.findById(id);
    }


    @Override
    public Reservation misAJour(Reservation reservation, Long Id) {
        return reservationRepository.findById(Id)
                .map((r)->{
                    r.setStatut(reservation.getStatut());
                    r.setVol(reservation.getVol());
                    r.setDateReservation(reservation.getDateReservation());
                    r.setDateAnnulation(reservation.getDateAnnulation());
                    r.setNombreDepassager(reservation.getNombreDepassager());
                    r.setRaisonAnnulation(reservation.getRaisonAnnulation());
                    return reservationRepository.save(r);
                }).orElseThrow(() -> new RuntimeException("Reservation introuvable"));
    }

    @Override
    public void supprimer(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservationsByPassager(Long utilisateurId) {
        return reservationRepository.findByutilisateurId(utilisateurId);
    }
}
