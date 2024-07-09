package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.validator.DestinazioneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinazioneService {

	@Autowired
	private DestinazioneRepository destinazioneRepository;
	@Autowired
	private DestinazioneValidator destinazioneValidator;

	public List<Destinazione> findAll() {
		return destinazioneRepository.findAll();
	}

	public Destinazione findById(Long id) {
		return destinazioneRepository.findById(id);
	}

	public void validateAndSave(Destinazione destinazione) {
		destinazioneValidator.validate(destinazione);
		destinazioneRepository.save(destinazione);
	}

	public Destinazione save(Destinazione destinazione){
		return destinazioneRepository.save(destinazione);
	}

	public void deleteById(Long id) {
		destinazioneRepository.deleteById(id);
	}

	public Destinazione findByNome(String nome){
		return destinazioneRepository.findByNome(nome);
	}
}