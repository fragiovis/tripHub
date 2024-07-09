package it.uniroma3.siw.validator;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtenteValidator {

    @Autowired
    private UtenteRepository utenteRepository;

    public void validate(Utente utente) {
        if (utenteRepository.findByUsername(utente.getUsername())!=null) {
            throw new IllegalArgumentException("Username già esistente.");
        }
    }
}