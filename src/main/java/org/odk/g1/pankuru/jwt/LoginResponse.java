package org.odk.g1.pankuru.jwt;

import lombok.Data;
import org.odk.g1.pankuru.Entity.Humain.Personne;
import java.util.List;

@Data
public class LoginResponse {
    private String jwtToken;
    private Personne username;
    private List<String> roles;

    public LoginResponse(Personne username, List<String> roles, String jwtToken) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }
}


