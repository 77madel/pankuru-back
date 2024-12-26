package org.odk.g1.pankuru.Entity.Permission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.odk.g1.pankuru.Entity.Humain.Personne;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Personne> personneList;

    @JsonIgnore
    @ManyToMany(mappedBy = "roleList")
    List<Permission> permissionList;


}
