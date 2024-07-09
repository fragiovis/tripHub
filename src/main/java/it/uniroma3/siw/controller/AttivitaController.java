package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Attivita;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.repository.AttivitaRepository;
import it.uniroma3.siw.service.AttivitaService;
import it.uniroma3.siw.service.DestinazioneService;
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
import java.util.List;

@Controller
public class AttivitaController {

	@Autowired
	private AttivitaService attivitaService ;
    @Autowired
    private DestinazioneService destinazioneService;

	@GetMapping("/utente/pubblica/attivita/{destinazioneId}")
	public String pubblicaAttivita(@PathVariable Long destinazioneId, Model model) {
		model.addAttribute("destinazione", destinazioneService.findById(destinazioneId));
		return "pubblicaAttivita.html";
	}

	@PostMapping("/utente/pubblica/attivita/{destinazioneId}")
	public String pubblicaAttivita(@RequestParam("nome") String nome,
									   @RequestParam("descrizione") String descrizione,
									   @RequestParam("immagine") MultipartFile immagine,
									   @PathVariable Long destinazioneId) {
		try {
			Destinazione destinazione = destinazioneService.findById(destinazioneId);
			if (destinazione == null) {
				return "redirect:/errore"; // Redirect to an error page if destination not found
			}
			String immagineFileName = saveImage(immagine);
			Attivita attivita = new Attivita(nome, descrizione, "/images/uploads/attivita-photos/" + immagineFileName, destinazione);
			destinazione.getAttivita().add(attivita);
			attivitaService.save(attivita); // Save the activity

			return "redirect:/utente/destinazione/" + destinazione.getId(); // Redirect to the destination page
		} catch (IOException e) {
			e.printStackTrace();
			return "errore.html"; // Handle the error by redirecting to an error page
		}
	}

	private String saveImage(MultipartFile immagine) throws IOException {
		String immagineFileName = System.currentTimeMillis() + "_" + immagine.getOriginalFilename(); // Assicurati che il nome del file sia unico
		Path uploadPath = Paths.get("src/main/resources/static/images/uploads/attivita-photos"); // Percorso assoluto

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
			System.out.println("Created directories: " + uploadPath.toString());
		}

		Path imageFilePath = uploadPath.resolve(immagineFileName);
		Files.write(imageFilePath, immagine.getBytes());
		System.out.println("Image saved at: " + imageFilePath.toString());

		return immagineFileName;
	}

}
