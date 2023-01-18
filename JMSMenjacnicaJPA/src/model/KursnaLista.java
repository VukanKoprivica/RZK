package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KursnaLista database table.
 * 
 */
@Entity
@NamedQuery(name="KursnaLista.findAll", query="SELECT k FROM KursnaLista k")
public class KursnaLista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKursnaLista;

	private double dolar;

	private double euro;

	public KursnaLista() {
	}

	public int getIdKursnaLista() {
		return this.idKursnaLista;
	}

	public void setIdKursnaLista(int idKursnaLista) {
		this.idKursnaLista = idKursnaLista;
	}

	public double getDolar() {
		return this.dolar;
	}

	public void setDolar(double dolar) {
		this.dolar = dolar;
	}

	public double getEuro() {
		return this.euro;
	}

	public void setEuro(double euro) {
		this.euro = euro;
	}

}