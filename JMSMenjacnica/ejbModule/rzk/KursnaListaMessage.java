package rzk;

import java.io.Serializable;

public class KursnaListaMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sender;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Double getEuro() {
		return euro;
	}
	public void setEuro(Double euro) {
		this.euro = euro;
	}
	public Double getDolar() {
		return dolar;
	}
	public void setDolar(Double dolar) {
		this.dolar = dolar;
	}
	private Double euro;
	private Double dolar;
	
}