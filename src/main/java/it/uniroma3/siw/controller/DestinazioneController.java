package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Itinerario;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.ItinerarioService;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

@Controller
public class DestinazioneController {

	@Autowired
	private DestinazioneService destinazioneService;
    @Autowired
    private ItinerarioService itinerarioService;

	@GetMapping("/destinazione/{id}")
	public String getDestinazione(@PathVariable Long id, Model model) {
		// Recupera la destinazione dal servizio utilizzando l'ID
		Destinazione destinazione = destinazioneService.findById(id);

		if (destinazione == null) {
			// Gestisci il caso in cui la destinazione non viene trovata
			return "redirect:/error"; // O un'altra pagina di errore
		}

		// Aggiungi la destinazione al modello per renderla disponibile nella vista
		model.addAttribute("destinazione", destinazione);

		// Indirizza verso la vista destinazione.html
		return "destinazione.html";
	}

	@GetMapping("/utente/destinazione/{id}")
	public String getDestinazioneUtente(@PathVariable Long id, Model model) {
		// Recupera la destinazione dal servizio utilizzando l'ID
		Destinazione destinazione = destinazioneService.findById(id);

		if (destinazione == null) {
			// Gestisci il caso in cui la destinazione non viene trovata
			return "redirect:/error"; // O un'altra pagina di errore
		}

		// Aggiungi la destinazione al modello per renderla disponibile nella vista
		model.addAttribute("destinazione", destinazione);

		// Indirizza verso la vista destinazione.html
		return "destinazione-utente.html";
	}


	@GetMapping("/utente/pubblica/destinazione")
	public String pubblicaDestinazione() {
		return "pubblicaDestinazione.html";
	}

	@PostMapping("/utente/pubblica/destinazione")
	public String pubblicaDestinazione(@RequestParam("nome") String nome,
									   @RequestParam("descrizione") String descrizione,
									   @RequestParam("immagine") MultipartFile immagine) {
		try {
			String immagineFileName = saveImage(immagine);
			Destinazione destinazione = new Destinazione(nome, descrizione, "/images/uploads/destinazione-photos/" + immagineFileName);
			destinazioneService.validateAndSave(destinazione); // Metodo del service per salvare la destinazione
			return "redirect:/utente/destinazione/" + destinazione.getId(); // Redirect alla homepage o alla pagina desiderata dopo aver aggiunto la destinazione
		} catch (IOException e) {
			e.printStackTrace();
			return "errore.html"; // Gestione dell'errore in caso di problemi nel salvataggio dell'immagine
		}
	}

	private String saveImage(MultipartFile immagine) throws IOException {
		String immagineFileName = System.currentTimeMillis() + "_" + immagine.getOriginalFilename(); // Assicurati che il nome del file sia unico
		Path uploadPath = Paths.get("src/main/resources/static/images/uploads/destinazione-photos"); // Percorso assoluto

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
			System.out.println("Created directories: " + uploadPath.toString());
		}

		Path imageFilePath = uploadPath.resolve(immagineFileName);
		Files.write(imageFilePath, immagine.getBytes());
		System.out.println("Image saved at: " + imageFilePath.toString());

		return immagineFileName;
	}

	@GetMapping("/destinazioni")
	private String showDestinazioni(Model model){
		model.addAttribute("destinazioni", this.destinazioneService.findAll());
		return "destinazioni";
	}

	@GetMapping("/utente/destinazioni")
	private String showDestinazioniUtente(Model model){
		model.addAttribute("destinazioni", this.destinazioneService.findAll());
		return "destinazioni-utente";
	}
}
