package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensioneService {

	@Autowired
	private RecensioneRepository recensioneRepository;

	public List<Recensione> findAll() {
		return recensioneRepository.findAll();
	}

	public Recensione findById(Long id) {
		return recensioneRepository.findById(id);
	}

	public Recensione save(Recensione recensione) {
		return recensioneRepository.save(recensione);
	}

	public void deleteById(Long id) {
		recensioneRepository.deleteById(id);
	}
}