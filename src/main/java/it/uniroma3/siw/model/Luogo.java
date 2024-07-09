package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Luogo {
	@Id
	@SequenceGenerator(name = "seq_luogo", sequenceName = "seq_luogo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_luogo")
	private Long id;

	private String nome;
	private String descrizione;
	private String immagine;

	@ManyToOne
	private Destinazione destinazione;

	public Luogo(String nome, String descrizione, String immagine, Destinazione destinazione) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.destinazione = destinazione;
	}

	public Luogo() {}

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

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Destinazione getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Destinazione destinazione) {
		this.destinazione = destinazione;
	}
}
