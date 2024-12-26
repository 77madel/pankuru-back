package org.odk.g1.pankuru.Controller.HumainController;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Humain.Faq;
import org.odk.g1.pankuru.Entity.Humain.Notification;
import org.odk.g1.pankuru.Repository.NotificationRepository;
import org.odk.g1.pankuru.Service.Service.HumainService.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class NotificationController {
    private NotificationService notificationService;
    @GetMapping("/afficher/{id}")
    public List<Notification> afficher(@PathVariable Long id){
        return notificationService.liste(id);
    }
}
