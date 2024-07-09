package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente,Long> {
    Utente findByUsername(String username);
    Optional<Utente> findById(Long id);
    List<Utente> findAll();

}
