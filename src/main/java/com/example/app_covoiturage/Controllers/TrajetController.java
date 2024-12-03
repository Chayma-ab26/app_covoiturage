package com.example.app_covoiturage.Controllers;

import com.example.app_covoiturage.Models.Trajet;
import com.example.app_covoiturage.Services.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trajets")
public class TrajetController {

    @Autowired
    private TrajetService trajetService;

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/all")
    public String afficherPageTrajets(Model model) {
        List<Trajet> trajets = trajetService.getAll();
        model.addAttribute("trajets", trajets);
        return "liste_trajets";
    }

    @GetMapping("/add")
    public String ajouterTrajetForm(Model model) {
        model.addAttribute("TrajetForm", new Trajet());
        return "add_trajet";
    }

    @PostMapping("/saveTrajet")
    public String saveTrajet(@ModelAttribute("TrajetForm") Trajet trajet) {
        trajetService.createTrajet(trajet);
        return "redirect:/trajets/all";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Optional<Trajet> trajetOptional = trajetService.getTrajetById(id);
        if (trajetOptional.isPresent()) {
            model.addAttribute("TrajetForm", trajetOptional.get());
            return "edit_trajet"; // Redirige vers un formulaire spécifique pour l'édition.
        } else {
            return "redirect:/trajets/all?error=notfound";
        }
    }

    @PostMapping("/updateTrajet/{id}")
    public String updateTrajet(@PathVariable("id") Long id, @ModelAttribute("TrajetForm") Trajet trajet) {
        trajet.setId(id);
        trajetService.createTrajet(trajet); // Ou utilisez un service dédié à la mise à jour.
        return "redirect:/trajets/all";
    }

    @GetMapping("/delete/{id}")
    public String supprimerTrajet(@PathVariable Long id) {
        trajetService.deleteById(id);
        return "redirect:/trajets/all";
    }
}
