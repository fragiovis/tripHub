package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Destinazione {
	@Id
	@SequenceGenerator(name = "seq_destinazione", sequenceName = "seq_destinazione", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_destinazione")
	private Long id;
	private String nome;
	private String descrizione;
	private String immagine;

	@OneToMany(mappedBy = "destinazione", cascade = CascadeType.ALL)
	private List<Luogo> luoghi = new ArrayList<>();

	@OneToMany(mappedBy = "destinazione", cascade = CascadeType.ALL)
	private List<Attivita> attivita = new ArrayList<>();

	@OneToMany
	private List<Itinerario> itinerari = new ArrayList<>();

	@ManyToMany
	private List<Cibo> cibi = new ArrayList<>();


	public Destinazione() {}

	public Destinazione(String nome, String descrizione, String immagine) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;

	}

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Luogo> getLuoghi() {
		return luoghi;
	}

	public void setLuoghi(List<Luogo> luoghi) {
		this.luoghi = luoghi;
	}

	public List<Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(List<Attivita> attivita) {
		this.attivita = attivita;
	}

	public List<Itinerario> getItinerari() {
		return itinerari;
	}

	public void setItinerari(List<Itinerario> itinerari) {
		this.itinerari = itinerari;
	}

	public List<Cibo> getCibi() {
		return cibi;
	}

	public void setCibi(List<Cibo> cibi) {
		this.cibi = cibi;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
}
