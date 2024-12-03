package com.example.app_covoiturage.Controllers;

import com.example.app_covoiturage.Models.User;
import com.example.app_covoiturage.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/auth")
public class AuthentificationController {

    @Autowired
    private UserService userService;


    @GetMapping("/login/user")
    public String loginUser(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        User existingUser = userService.findByEmail(user.getEmail()).orElse(null);

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            if (existingUser.getRole() == User.Role.conducteur) {
                return "redirect:/auth/dashboard/conducteur";
            } else if (existingUser.getRole() == User.Role.passager) {
                return "redirect:/auth/dashboard/passager";
            }
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/dashboard/conducteur")
    public String conducteurDashboard() {
        return "dashboard_conducteur";
    }

    @GetMapping("/dashboard/passager")
    public String passagerDashboard() {
        return "dashboard_passager";
    }
    @GetMapping("/register/conducteur")
    public String registerConducteur(Model model) {
        User user = new User();
        user.setRole(User.Role.conducteur);
        model.addAttribute("userForm", user);
        return "register_conducteur";
    }

    @GetMapping("/register/passager")
    public String registerPassager(Model model) {
        User user = new User();
        user.setRole(User.Role.passager);
        model.addAttribute("userForm", user);
        return "register_passager";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userForm") User user,
                               @RequestParam("vehicleImage") MultipartFile vehicleImage) {
        if (!vehicleImage.isEmpty()) {
            try {
                // Obtenez le nom original du fichier
                String fileName = vehicleImage.getOriginalFilename();

                // Définir le chemin de sauvegarde
                String uploadDir = "uploads/";
                File uploadPath = new File(uploadDir);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs(); // Créez le répertoire si nécessaire
                }

                // Sauvegarder le fichier
                Path path = Paths.get(uploadDir + fileName);
                Files.copy(vehicleImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                // Enregistrer le chemin du fichier dans l'entité utilisateur
                user.setFileName(uploadDir + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/auth/register?error=file_upload";
            }
        }

        userService.save(user);
        return "redirect:/auth/login";
    }
}
