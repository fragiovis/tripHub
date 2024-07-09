package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Attivita;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttivitaRepository extends CrudRepository<Attivita, Integer> {
    List<Attivita> findAll();
    void deleteById(Long id);
    Attivita findById(Long id);
}
