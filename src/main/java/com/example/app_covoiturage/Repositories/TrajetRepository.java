package com.example.app_covoiturage.Repositories;

import com.example.app_covoiturage.Models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    List<Trajet> findByUserId(Long userId);
    List<Trajet> findByDepartAndDestination(String depart, String destination);
    void deleteByUserId(Long userId);
}