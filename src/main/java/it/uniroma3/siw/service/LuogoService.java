package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Luogo;
import it.uniroma3.siw.repository.LuogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuogoService {

	@Autowired
	private LuogoRepository luogoRepository;

	public List<Luogo> findAll() {
		return luogoRepository.findAll();
	}

	public Luogo findById(Long id) {
		return luogoRepository.findById(id).orElse(null);
	}

	public Luogo save(Luogo luogo) {
		return luogoRepository.save(luogo);
	}

	public void deleteById(Long id) {
		luogoRepository.deleteById(id);
	}
}
