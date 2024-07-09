package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Cibo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CiboRepository extends CrudRepository<Cibo, Integer> {
    Cibo findById(Long id);
    void deleteById(Long id);
    List<Cibo> findAll();
}
