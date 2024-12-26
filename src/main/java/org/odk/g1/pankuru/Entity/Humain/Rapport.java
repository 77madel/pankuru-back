package org.odk.g1.pankuru.Entity.Humain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Column(length = 4000)
    private String text;
    private Date dateCreation;
    private String type;
    private String url;
    @Column(length = 4000)
    private String commentaires;

    @ManyToOne
    @JoinColumn(name = "adminCompagnie_id")
    private AdminCompagnie adminCompagnie;

}
