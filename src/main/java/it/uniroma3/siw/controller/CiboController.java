package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Cibo;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.service.CiboService;
import it.uniroma3.siw.service.DestinazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CiboController {

	@Autowired
	private DestinazioneService destinazioneService;

	@Autowired
	private CiboService ciboService;

	@GetMapping("/utente/pubblica/cibo/{id}")
	public String getDestinazione(@PathVariable Long id, Model model) {
		Destinazione destinazione = destinazioneService.findById(id);

		if (destinazione == null) {
			return "redirect:/error";
		}

		List<Cibo> cibi = ciboService.findAll();
		List<Cibo> destinazioneCibi = destinazione.getCibi();

		// Rimuovo i cibi già associati alla destinazione dalla lista di tutti i cibi
		cibi.removeAll(destinazioneCibi);

		model.addAttribute("destinazione", destinazione);
		model.addAttribute("cibi", cibi);

		return "pubblicaCibo";
	}

	@PostMapping("/utente/pubblica/cibo/{destinazioneId}")
	public String pubblicaCibo(@RequestParam(value = "nome", required = false) String nome,
							   @RequestParam(value = "descrizione", required = false) String descrizione,
							   @RequestParam(value = "immagine", required = false) MultipartFile immagine,
							   @RequestParam("ciboEsistente") Long ciboEsistenteId,
							   @PathVariable Long destinazioneId) {
		try {
			Destinazione destinazione = destinazioneService.findById(destinazioneId);
			if (destinazione == null) {
				return "redirect:/errore";
			}

			Cibo cibo;
			if (ciboEsistenteId != null && ciboEsistenteId != 0) {
				// Se un cibo esistente è stato selezionato
				cibo = ciboService.findById(ciboEsistenteId);
				if (cibo == null) {
					return "redirect:/errore"; // Gestisci il caso in cui il cibo selezionato non esiste
				}
			} else {
				// Creazione di un nuovo cibo
				String immagineFileName = saveImage(immagine);
				cibo = new Cibo(nome, descrizione, "/images/uploads/cibo-photos/" + immagineFileName, destinazione);
				ciboService.save(cibo);
			}

			destinazione.getCibi().add(cibo);
			destinazioneService.save(destinazione);

			return "redirect:/utente/destinazione/" + destinazione.getId();
		} catch (IOException e) {
			e.printStackTrace();
			return "errore";
		}
	}


	private String saveImage(MultipartFile immagine) throws IOException {
		String immagineFileName = System.currentTimeMillis() + "_" + immagine.getOriginalFilename();
		Path uploadPath = Paths.get("src/main/resources/static/images/uploads/cibo-photos");

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		Path imageFilePath = uploadPath.resolve(immagineFileName);
		Files.write(imageFilePath, immagine.getBytes());

		return immagineFileName;
	}
}
