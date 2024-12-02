package com.example.app_covoiturage.Models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Entity
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String depart;
    private String destination;
    private int nbPlaces;
    private String fileName;
    private String type;

    private LocalDate datePublication;
    private LocalDate dateDepart;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "trajet")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "trajet")
    private List<Commentaire> commentaires;


}