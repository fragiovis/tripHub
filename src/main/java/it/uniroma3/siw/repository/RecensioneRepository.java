package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Recensione;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RecensioneRepository extends CrudRepository<Recensione, Integer> {
    List<Recensione> findAll();
    Recensione findById(Long id);
    void deleteById(Long id);

}
