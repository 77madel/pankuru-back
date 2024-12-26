package org.odk.g1.pankuru.Service.Service.HumainService;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Repository.HumainRepo.PersonneRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class PersonneService implements UserDetailsService {
    private PersonneRepo personneRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.personneRepo
                .findByEmail(username)
                .orElseThrow(() -> new  UsernameNotFoundException("Aucun utilisateur ne corespond à cet identifiant")
                );
    }

}
