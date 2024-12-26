package org.odk.g1.pankuru.Service.Service;

import java.util.Optional;

import org.odk.g1.pankuru.Entity.Humain.Personne;
import org.odk.g1.pankuru.Repository.HumainRepo.AdminCompagnieRepo;
import org.odk.g1.pankuru.Repository.HumainRepo.PersonneRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {
    private final PersonneRepo personneRepo;
    private final AdminCompagnieRepo adminCompagnieRepository;

    public Long getCurrentUsernameId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Personne> user = personneRepo.findByEmail(authentication.getName());
        Long id = 0L;
        if (user.isPresent()) {
            id = user.get().getId();
        }
        
        return id;
    }

    public Integer getCompagnieId(){
        return adminCompagnieRepository.findCompagnieIdByAdminId(getCurrentUsernameId());
    }
}
