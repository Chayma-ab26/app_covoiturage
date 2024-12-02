package com.example.app_covoiturage.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String numTel;
    private String fileName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Trajet> trajets;

    @OneToMany(mappedBy = "user")
    private List<Commentaire> commentaires;


    public enum Role {
        conducteur, passager
    }

}



