package it.uniroma3.siw.model;
import java.util.*;
import java.util.Objects;

import jakarta.persistence.*;


@Entity
public class Itinerario {
	@Id
	@SequenceGenerator(name = "seq_itinerario", sequenceName = "seq_itinerario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_itinerario")
	private Long id;
	@OneToMany(mappedBy = "itinerario")
	private List<Recensione> recensioni;
	@ManyToOne
	private Destinazione destinazione;
	private String nome;
	@Column(length = 10000)
	private String descrizione;
	private Integer numgiorni;
	public Itinerario() {}

	public Itinerario(String nome, String descrizione, Destinazione destinazione, Integer numgiorni) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.destinazione = destinazione;
		this.numgiorni = numgiorni;
	}

	public Destinazione getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Destinazione destinazione) {
		this.destinazione = destinazione;
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

	public Integer getNumgiorni() {
		return numgiorni;
	}

	public void setNumgiorni(Integer numgiorni) {
		this.numgiorni = numgiorni;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Recensione> getRecensioni() {
		return recensioni;
	}
	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, recensioni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Objects.equals(id, other.id) && Objects.equals(recensioni, other.recensioni);
	}
	
}
	
