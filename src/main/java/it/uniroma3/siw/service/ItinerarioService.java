package it.uniroma3.siw.service;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Itinerario;
import it.uniroma3.siw.repository.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItinerarioService {

	@Autowired
	private ItinerarioRepository itinerarioRepository;

	public List<Itinerario> findAll() {
		return itinerarioRepository.findAll();
	}

	public Itinerario findById(Long id) {
		return itinerarioRepository.findById(id).orElse(null);
	}

	public Itinerario save(Itinerario itinerario) {
		return itinerarioRepository.save(itinerario);
	}

	public void deleteById(Long id) {
		itinerarioRepository.deleteById(id);
	}

	public List<Itinerario> findByDestinazione(Destinazione destinazione) {
		return itinerarioRepository.findByDestinazione(destinazione);
	}

	public List<Itinerario> findBynumgiorniAndDestinazione(int numgiorni, Destinazione destinazione){
		return itinerarioRepository.findBynumgiorniAndDestinazione(numgiorni, destinazione);
	}
}
