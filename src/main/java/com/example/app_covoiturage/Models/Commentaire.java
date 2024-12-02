package com.example.app_covoiturage.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int likeCount;
    private int dislikeCount;
    private Date datePublication;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;


}