package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ConferencePredavac database table.
 * 
 */
@Entity
@NamedQuery(name="ConferencePredavac.findAll", query="SELECT c FROM ConferencePredavac c")
public class ConferencePredavac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idConferencePredavac;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Conference
	@ManyToOne
	@JoinColumn(name="conf_pred")
	private Conference conference;

	public ConferencePredavac() {
	}

	public int getIdConferencePredavac() {
		return this.idConferencePredavac;
	}

	public void setIdConferencePredavac(int idConferencePredavac) {
		this.idConferencePredavac = idConferencePredavac;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Conference getConference() {
		return this.conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

}