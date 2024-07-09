package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Luogo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LuogoRepository extends CrudRepository<Luogo, Long> {

    Optional<Luogo> findById(Long id);
    List<Luogo> findAll();
}
