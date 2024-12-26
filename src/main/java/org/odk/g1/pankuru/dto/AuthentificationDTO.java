package org.odk.g1.pankuru.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
// import org.odk.g1.pankuru.Entity.Humain.Adresse;
import org.odk.g1.pankuru.Entity.Humain.Personne;
// import org.odk.g1.pankuru.Entity.Permission.Role;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthentificationDTO {

    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private List<String> role;
    private Personne personne;
    private List<Personne> personneList;
}
