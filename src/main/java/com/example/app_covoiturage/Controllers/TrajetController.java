package com.example.app_covoiturage.Controllers;

import com.example.app_covoiturage.Models.Trajet;
import com.example.app_covoiturage.Services.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/trajets")
public class TrajetController {
    @Autowired
    TrajetService trajetService;
    @RequestMapping("/accueil")
    public String accueil(Model model) {
        return "accueil";
    }

    @RequestMapping("/all")
    public String afficherPageTrajets(Model model) {
        List<Trajet> trajets = trajetService.getAll();
        model.addAttribute("trajets", trajets);
        return "liste_trajets";
    }
    @GetMapping("/add")
    public String ajouterTrajet(Model model) {
        Trajet trajet = new Trajet();
        model.addAttribute("TrajetForm", trajet);
        return "add_trajet";
    }
    @PostMapping("/saveTrajet")
    public String saveTrajet(@ModelAttribute("TrajetForm") Trajet trajet) {
        trajetService.createTrajet(trajet);
        return "redirect:/trajets/all";
    }

    @GetMapping("edit/{id}")
    public String UpdateForm(@PathVariable("id")long id, Model model){
        Trajet trajet = trajetService.getTrajetById(id).get();
        model.addAttribute("TrajetForm", trajet);
        return "redirect:/trajets/all";
    }

    @GetMapping("/delete/{id}")
    public String supprimerTrajet(@PathVariable long id) {
        trajetService.deleteById(id);
        return "redirect:/trajets/all";
    }

}

