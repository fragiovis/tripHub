package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Attivita;
import it.uniroma3.siw.repository.AttivitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttivitaService {

	@Autowired
	private AttivitaRepository attivitaRepository;

	public List<Attivita> findAll() {
		return attivitaRepository.findAll();
	}

	public Attivita findById(Long id) {
		return attivitaRepository.findById(id);
	}

	public Attivita save(Attivita attivita) {
		return attivitaRepository.save(attivita);
	}

	public void deleteById(Long id) {
		attivitaRepository.deleteById(id);
	}
}
