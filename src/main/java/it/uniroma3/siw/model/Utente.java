package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Utente {

    @Id
    @SequenceGenerator(name = "seq_utente", sequenceName = "seq_utente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_utente")
    private Long id;
    private String ruolo;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String immagine;

    public List<Itinerario> getItinerari() {
        return itinerari;
    }

    public void setItinerari(List<Itinerario> itinerari) {
        this.itinerari = itinerari;
    }

    @OneToMany
    private List<Itinerario> itinerari = new ArrayList<>();


    public Utente() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    @OneToMany(mappedBy = "utente")
    private List<Recensione> recensioni;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}