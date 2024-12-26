package org.odk.g1.pankuru.Controller.HumainController;

import org.odk.g1.pankuru.Entity.Humain.Personne;
import org.odk.g1.pankuru.Repository.HumainRepo.PersonneRepo;
import org.odk.g1.pankuru.jwt.JwtUtils;
import org.odk.g1.pankuru.jwt.LoginRequest;
import org.odk.g1.pankuru.jwt.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personne")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class PersonneController {

    private final AuthenticationManager authenticationManager;
    private final PersonneRepo personneRepo;
    private JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Personne fullName = new Personne();
        Optional<Personne> personne = personneRepo.findByEmail(userDetails.getUsername());
        if (personne.isPresent()){
            fullName = personne.get();
        }
        LoginResponse response = new LoginResponse(fullName, roles, jwtToken);

        return ResponseEntity.ok(response);
    }

}
