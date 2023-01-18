package rzk;

import java.io.Serializable;

public class CardMessage implements Serializable {

	private String cardFrom;

	private String cardTo;

	private String email;

	private String type;

	public CardMessage() {
		
	}

	public CardMessage(String cardFrom, String cardTo, String email, String type) {
		super();
		this.cardFrom = cardFrom;
		this.cardTo = cardTo;
		this.email = email;
		this.type = type;
	}

	public String getCardFrom() {
		return cardFrom;
	}

	public void setCardFrom(String cardFrom) {
		this.cardFrom = cardFrom;
	}

	public String getCardTo() {
		return cardTo;
	}

	public void setCardTo(String cardTo) {
		this.cardTo = cardTo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CardMessage [cardFrom=" + cardFrom + ", cardTo=" + cardTo + ", email=" + email + ", type=" + type + "]";
	}
	
	
}
