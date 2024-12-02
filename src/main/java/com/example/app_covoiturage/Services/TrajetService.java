package com.example.app_covoiturage.Services;

import com.example.app_covoiturage.Models.Trajet;
import com.example.app_covoiturage.Repositories.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {
    @Autowired
    private TrajetRepository trajetRepository;


    public Trajet createTrajet(Trajet trajet) {
        return trajetRepository.save(trajet);
    }
    public List<Trajet> getAll() {
        return trajetRepository.findAll();
    }

    public Optional<Trajet> getTrajetById(Long id) {
        return trajetRepository.findById(id);
    }

    public Trajet save(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    public void deleteById(Long id) {
        trajetRepository.deleteById(id);
    }

}
