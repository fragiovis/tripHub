package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Destinazione;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DestinazioneRepository extends CrudRepository<Destinazione, Integer> {
    List<Destinazione> findAll();
    Destinazione findById(Long id);
    void deleteById(Long id);
    Destinazione findByNome(String nome);
}