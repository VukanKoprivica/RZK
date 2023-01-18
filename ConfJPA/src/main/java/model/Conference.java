package model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;



/**
 * The persistent class for the Conference database table.
 * 
 */
@Entity
@NamedQuery(name="Conference.findAll", query="SELECT c FROM Conference c")
public class Conference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idConference;

	private String city;

	private String country;

	private String countryCode;

	private String currency;

	@Temporal(TemporalType.DATE)
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	private Date dateTo;

	private String dialingCode;

	private String field;

	private String title;

	//bi-directional many-to-one association to ConferencePredavac
	@OneToMany(mappedBy="conference",fetch = FetchType.EAGER)
	@JsonbTransient
	private List<ConferencePredavac> conferencePredavacs;

	public Conference() {
	}

	public int getIdConference() {
		return this.idConference;
	}

	public void setIdConference(int idConference) {
		this.idConference = idConference;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getDialingCode() {
		return this.dialingCode;
	}

	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ConferencePredavac> getConferencePredavacs() {
		return this.conferencePredavacs;
	}

	public void setConferencePredavacs(List<ConferencePredavac> conferencePredavacs) {
		this.conferencePredavacs = conferencePredavacs;
	}

	public ConferencePredavac addConferencePredavac(ConferencePredavac conferencePredavac) {
		getConferencePredavacs().add(conferencePredavac);
		conferencePredavac.setConference(this);

		return conferencePredavac;
	}

	public ConferencePredavac removeConferencePredavac(ConferencePredavac conferencePredavac) {
		getConferencePredavacs().remove(conferencePredavac);
		conferencePredavac.setConference(null);

		return conferencePredavac;
	}

	@Override
	public String toString() {
		return "Conference [idConference=" + idConference + ", city=" + city + ", country=" + country + ", countryCode="
				+ countryCode + ", currency=" + currency + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", dialingCode=" + dialingCode + ", field=" + field + ", title=" + title + ", conferencePredavacs="
				+ conferencePredavacs + "]";
	}
	
	

}