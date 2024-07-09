package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private DestinazioneService destinazioneService;
    @Autowired
    private DestinazioneRepository destinazioneRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("destinazioni", destinazioneRepository.findAll());
        return "index.html";
    }

    @GetMapping("/utente/index")
    public String indexUtente(Model model) {
        model.addAttribute("destinazioni", destinazioneRepository.findAll());
        return "index-utente.html";
    }

    @GetMapping("/utente/pubblica")
    public String pubblicaUtente(Model model){
        model.addAttribute("destinazioni", destinazioneRepository.findAll());
        return "pubblica.html";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("destinazioni", destinazioneRepository.findAll());
        return "index.html";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nome del file HTML della pagina di login
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUtente(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String nome,
                               @RequestParam String cognome,
                               @RequestParam("immagine") MultipartFile immagine,
                               Model model) {
        try {
            String immagineFileName = saveImage(immagine);
            utenteService.registerUser(username, password, nome, cognome, immagineFileName);
            return "/login";
        } catch (Exception e) {
            model.addAttribute("error", "Username già esistente");
            return "register";  // Assumi che ci sia una pagina di registrazione per gestire gli errori
        }
    }

    private String saveImage(MultipartFile immagine) throws IOException {
        String immagineFileName = System.currentTimeMillis() + "_" + immagine.getOriginalFilename(); // Assicurati che il nome del file sia unico
        Path uploadPath = Paths.get("src/main/resources/static/images/uploads/utente-photos"); // Percorso assoluto

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
            System.out.println("Created directories: " + uploadPath.toString());
        }

        Path imageFilePath = uploadPath.resolve(immagineFileName);
        Files.write(imageFilePath, immagine.getBytes());
        System.out.println("Image saved at: " + imageFilePath.toString());

        return immagineFileName;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalida la sessione esistente
        }
        return "redirect:/"; // Reindirizza alla pagina home dopo il logout
    }
}