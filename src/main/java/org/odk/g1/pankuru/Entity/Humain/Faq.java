package org.odk.g1.pankuru.Entity.Humain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Faq{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String questionCategorie;
    @Column(nullable = false, length = 4000)
    private String reponse;

    @ManyToOne
    @JoinColumn(name = "adminCompagnie_id")
    private AdminCompagnie adminCompagnie;
}
