package it.uniroma3.siw.controller;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Itinerario;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.ItinerarioService;
import it.uniroma3.siw.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
public class ItinerarioController {

	@Autowired
	private ItinerarioService itinerarioService;
	@Autowired
	private DestinazioneService destinazioneService;
	@Autowired
	private UtenteService utenteService;

	@GetMapping("/utente/destinazione/{id}/itinerario")
	public String cercaItinerari(@PathVariable Long id, Model model) {
		Destinazione destinazione = destinazioneService.findById(id);
		if (destinazione == null) {
			return "redirect:/errore"; // Redirect to an error page if destination not found
		}

		List<Itinerario> itinerari = itinerarioService.findByDestinazione(destinazione);
		// Add destination and other necessary data to the model
		model.addAttribute("itinerari", itinerari);
		model.addAttribute("destinazione", destinazione);
		// Logic to generate the itinerary can be added here
		// For now, we are just forwarding to a view
		return "cercaItinerari"; // Name of the Thymeleaf template to render the itinerary
	}

	@GetMapping("/utente/itinerario/{id}")
	public String showItinerario(@PathVariable Long id, Model model, Principal principal) {
		Itinerario itinerario = itinerarioService.findById(id);
		model.addAttribute("itinerario", itinerario);

		if (principal != null) {
			Utente utente = utenteService.findByUsername(principal.getName());
			boolean itinerarioSalvato = utente.getItinerari().contains(itinerario);
			model.addAttribute("itinerarioSalvato", itinerarioSalvato);
		}

		return "itinerario";
	}

	@GetMapping("/utente/aggiungiItinerario/{id}")
	public String aggiungiItinerario(@PathVariable Long id, Model model) {
		// Recupera la destinazione dal servizio utilizzando l'ID
		Destinazione destinazione = destinazioneService.findById(id);

		if (destinazione == null) {
			// Gestisci il caso in cui la destinazione non viene trovata
			return "redirect:/error"; // O un'altra pagina di errore
		}

		// Aggiungi la destinazione al modello per renderla disponibile nella vista
		model.addAttribute("destinazione", destinazione);

		// Indirizza verso la vista pubblicaLuogo.html
		return "aggiungiItinerario";
	}

	@PostMapping("/utente/aggiungiItinerario/{id}")
	public String aggiungiIterinario(@RequestParam("nome") String nome,
									 @RequestParam("descrizione") String descrizione,
									 @PathVariable Long id,
									 @RequestParam Integer numgiorni) {
		Destinazione destinazione = destinazioneService.findById(id);

		Itinerario itinerario = new Itinerario(nome, descrizione, destinazione, numgiorni);
		itinerarioService.save(itinerario); // Metodo del service per salvare la destinazione
		return "redirect:/utente/itinerario/" + itinerario.getId(); // Redirect alla homepage o alla pagina desiderata dopo aver aggiunto la destinazione

	}

}