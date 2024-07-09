package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Luogo;
import it.uniroma3.siw.repository.LuogoRepository;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.LuogoService;
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
public class LuogoController {

	@Autowired
	private LuogoService luogoService;
	@Autowired
	private DestinazioneService destinazioneService;

	@GetMapping("/utente/pubblica/luogo/{id}")
	public String getDestinazione(@PathVariable Long id, Model model) {
		// Recupera la destinazione dal servizio utilizzando l'ID
		Destinazione destinazione = destinazioneService.findById(id);

		if (destinazione == null) {
			// Gestisci il caso in cui la destinazione non viene trovata
			return "redirect:/error"; // O un'altra pagina di errore
		}

		// Aggiungi la destinazione al modello per renderla disponibile nella vista
		model.addAttribute("destinazione", destinazione);

		// Indirizza verso la vista pubblicaLuogo.html
		return "pubblicaLuogo";
	}

	@PostMapping("/utente/pubblica/luogo/{destinazioneId}")
	public String pubblicaLuogo(@RequestParam("nome") String nome,
								@RequestParam("descrizione") String descrizione,
								@RequestParam("immagine") MultipartFile immagine,
								@PathVariable Long destinazioneId) {
		try {
			Destinazione destinazione = destinazioneService.findById(destinazioneId);
			if (destinazione == null) {
				return "redirect:/errore"; // Redirect to an error page if destination not found
			}
			String immagineFileName = saveImage(immagine);
			Luogo luogo = new Luogo(nome, descrizione, "/images/uploads/luogo-photos/" + immagineFileName, destinazione);
			destinazione.getLuoghi().add(luogo);
			luogoService.save(luogo); // Save the place

			return "redirect:/utente/destinazione/" + destinazione.getId(); // Redirect to the destination page
		} catch (IOException e) {
			e.printStackTrace();
			return "errore"; // Handle the error by redirecting to an error page
		}
	}

	private String saveImage(MultipartFile immagine) throws IOException {
		String immagineFileName = System.currentTimeMillis() + "_" + immagine.getOriginalFilename(); // Ensure the file name is unique
		Path uploadPath = Paths.get("src/main/resources/static/images/uploads/luogo-photos"); // Absolute path

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
