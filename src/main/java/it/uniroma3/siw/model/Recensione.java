package it.uniroma3.siw.model;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
public class Recensione {
	@Id
	@SequenceGenerator(name = "seq_recensione", sequenceName = "seq_recensione", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_recensione")
	private Long id;

	private String commento;
	private Integer voto;


	@ManyToOne
	private Itinerario itinerario;

	@ManyToOne
	private Utente utente;

	public Recensione() {}

	public Recensione(Integer voto, String commento, Itinerario itinerario, Utente utente) {
		this.voto = voto;
		this.commento = commento;
		this.itinerario = itinerario;
		this.utente = utente;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}


	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commento, id, itinerario, voto, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(commento, other.commento) && Objects.equals(id, other.id)
				&& Objects.equals(itinerario, other.itinerario)
				&& Objects.equals(voto, other.voto) && Objects.equals(utente, other.utente);
	}
}
