package org.odk.g1.pankuru.Service.Service.HumainService;

import org.odk.g1.pankuru.Entity.Humain.Faq;
import org.odk.g1.pankuru.Entity.Humain.Notification;
import org.odk.g1.pankuru.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> liste(Long utilisateurId) {
        return notificationRepository.findByUtilisateurId(utilisateurId);
    }
}
