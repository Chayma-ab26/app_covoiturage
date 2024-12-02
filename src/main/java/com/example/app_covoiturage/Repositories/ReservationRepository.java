package com.example.app_covoiturage.Repositories;

import com.example.app_covoiturage.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByTrajetId(Long trajetId);
    void deleteByTrajetId(Long trajetId);
}