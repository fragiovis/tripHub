package it.uniroma3.siw.validator;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.DestinazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DestinazioneValidator {

    @Autowired
    private DestinazioneRepository destinazioneRepository;

    public void validate(Destinazione destinazione) {
        if (destinazioneRepository.findByNome(destinazione.getNome())!=null) {
            throw new IllegalArgumentException("Destinazione già esistente.");
        }
    }
}