package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Itinerario;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.service.ItinerarioService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping("/utente/profilo")
    public String showProfilo(Model model) {
        // Ottieni l'utente autenticato
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Recupera le informazioni dell'utente dal database
        Utente utente = utenteService.findByUsername(currentUsername);
        List<Itinerario> itinerari = utente.getItinerari();

        // Aggiungi le informazioni dell'utente e gli itinerari al modello
        model.addAttribute("utente", utente);
        model.addAttribute("itinerari", itinerari);

        // Restituisci il nome della vista (template Thymeleaf) per la pagina del profilo
        return "profilo";
    }

    @PostMapping("/utente/toggleSalvato/{itinerarioId}")
    public String toggleSalvato(@PathVariable Long itinerarioId, Principal principal) {
        Utente utente = utenteService.findByUsername(principal.getName());
        Itinerario itinerario = itinerarioService.findById(itinerarioId);

        boolean salvato = utenteService.toggleSalvato(utente, itinerario);

        // Reindirizza alla pagina dell'itinerario
        return "redirect:/utente/itinerario/" + itinerarioId;
    }

    @PostMapping("/utente/profilo/modificaDati")
    public String modifyUserData(@RequestParam String nome, @RequestParam String cognome, Model model, Principal principal) {
        // Recupera l'utente dalla sessione o dal database
        String currentUsername = principal.getName();
        Utente utente = utenteService.findByUsername(currentUsername); // recupera l'utente dal database o dalla sessione
        // Aggiorna i dati dell'utente
        utente.setNome(nome);
        utente.setCognome(cognome);
        // Salva le modifiche nel database
        utenteService.save(utente); // ad esempio, salva l'utente nel database
        // Aggiungi l'utente aggiornato al modello
        model.addAttribute("utente", utente);
        return "redirect:/utente/profilo"; // reindirizza alla pagina del profilo
    }
}