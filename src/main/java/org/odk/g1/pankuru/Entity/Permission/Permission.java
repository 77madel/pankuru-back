package org.odk.g1.pankuru.Entity.Permission;

import jakarta.persistence.*;
import lombok.Data;
import org.odk.g1.pankuru.Entity.Enum.EnumPermission;

import java.util.List;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String endPoint;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumPermission permission;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;
}
