package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Cibo;
import it.uniroma3.siw.repository.CiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiboService {

	@Autowired
	private CiboRepository ciboRepository;

	public List<Cibo> findAll() {
		return ciboRepository.findAll();
	}

	public Cibo findById(Long id) {
		return ciboRepository.findById(id);
	}

	public Cibo save(Cibo cibo) {
		return ciboRepository.save(cibo);
	}

	public void deleteById(Long id) {
		ciboRepository.deleteById(id);
	}
}
