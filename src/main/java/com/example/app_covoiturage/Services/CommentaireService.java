package com.example.app_covoiturage.Services;

import com.example.app_covoiturage.Models.Commentaire;
import com.example.app_covoiturage.Repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    public Optional<Commentaire> getById(Long id) {
        return commentaireRepository.findById(id);
    }

    public Commentaire save(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public void deleteById(Long id) {
        commentaireRepository.deleteById(id);
    }
}