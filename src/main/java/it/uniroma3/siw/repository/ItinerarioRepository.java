package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Itinerario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItinerarioRepository extends CrudRepository<Itinerario, Long> {
    Itinerario findByNome(String nome);
    Optional<Itinerario> findById(Long id);
    void deleteById(Long id);
    List<Itinerario> findAll();
    List<Itinerario> findByDestinazione(Destinazione destinazione);

    List<Itinerario> findBynumgiorniAndDestinazione(int numgiorni, Destinazione destinazione);
}
