package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Cibo {
	@Id
	@SequenceGenerator(name = "seq_cibo", sequenceName = "seq_cibo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cibo")
	private Long id;

	private String nome;
	private String descrizione;
	private String immagine;

	@ManyToMany(mappedBy = "cibi")
	private List<Destinazione> destinazioni = new ArrayList<>();
	public Cibo() {}

	public Cibo(String nome, String descrizione, String immagine, Destinazione destinazione) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.destinazioni.add(destinazione);
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

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public List<Destinazione> getDestinazioni() {
		return destinazioni;
	}

	public void setDestinazioni(List<Destinazione> destinazioni) {
		this.destinazioni = destinazioni;
	}
}
