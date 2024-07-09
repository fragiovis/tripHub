package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Itinerario;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.validator.UtenteValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UtenteValidator utenteValidator;

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> findById(Long id) {
        return utenteRepository.findById(id);
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void deleteById(Long id) {
        utenteRepository.deleteById(id);
    }

    public Utente findByUsername(String username){
        return utenteRepository.findByUsername(username);
    }

    @Transactional
    public Utente registerUser(String username, String password, String firstName, String lastName, String immagineFileName) {
        String encodedPassword = passwordEncoder.encode(password);

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setNome(firstName);
        utente.setRuolo("ROLE_UTENTE");
        utente.setCognome(lastName);
        utente.setPassword(encodedPassword);
        utente.setImmagine("/images/uploads/utente-photos/" + immagineFileName); // Percorso completo

        utenteValidator.validate(utente);
        return utenteRepository.save(utente);
    }

    public boolean toggleSalvato(Utente utente, Itinerario itinerario) {
        List<Itinerario> itinerari = utente.getItinerari();
        boolean salvato;
        if (itinerari.contains(itinerario)) {
            itinerari.remove(itinerario);
            salvato = false;
        } else {
            itinerari.add(itinerario);
            salvato = true;
        }
        utenteRepository.save(utente);
        return salvato;
    }

}