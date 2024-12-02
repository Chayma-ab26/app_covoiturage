package com.example.app_covoiturage.Repositories;

import com.example.app_covoiturage.Models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByUserId(Long userId);
    List<Commentaire> findByTrajetId(Long trajetId);
    void deleteByTrajetId(Long trajetId);
}
