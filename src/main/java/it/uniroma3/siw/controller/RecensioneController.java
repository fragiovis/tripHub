package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.RecensioneRepository;
import it.uniroma3.siw.service.ItinerarioService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
public class RecensioneController {

	@Autowired
	private RecensioneService recensioneService;
	@Autowired
	private ItinerarioService itinerarioService;
	@Autowired
	private UtenteService utenteService;

	@GetMapping("/utente/aggiungiRecensione/{id}")
	public String aggiungiRecensione(@PathVariable Long id, Model model) {
		model.addAttribute("itinerario", itinerarioService.findById(id));
		return "aggiungiRecensione";
	}

	@PostMapping("/utente/aggiungiRecensione/{id}")
	public String aggiungiRecensione(@RequestParam("voto") Integer voto,
									 @RequestParam("commento") String commento,
									 @PathVariable Long id,
									 Principal principal) {
		try {
			Itinerario itinerario = itinerarioService.findById(id);
			if (itinerario == null) {
				return "redirect:/errore"; // Redirect to an error page if itinerary not found
			}

			String username = principal.getName();
			Utente utente = utenteService.findByUsername(username);
			if (utente == null) {
				return "redirect:/errore"; // Redirect to an error page if user not found
			}

			Recensione recensione = new Recensione(voto, commento, itinerario, utente);
			recensioneService.save(recensione);
			return "redirect:/utente/itinerario/" + itinerario.getId(); // Redirect to the itinerary page
		} catch (Exception e) {
			e.printStackTrace();
			return "errore"; // Handle the error by redirecting to an error page
		}
	}

}