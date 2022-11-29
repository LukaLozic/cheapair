package com.cheapair.dbmodels;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FLIGHTS")
@Data
@NoArgsConstructor
public class Flight {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FLIGHT", nullable = false, columnDefinition = "integer")
    @Setter(value = AccessLevel.PROTECTED)
	private Integer idFlight;
	
    @ManyToOne
    @JoinColumn(name="departure_airport_id", nullable=false)
	private Airport departureAirport;
    
    @ManyToOne
    @JoinColumn(name="arrival_airport_id", nullable=false)
	private Airport arrivalAirport;
    
	@Column(name = "DEPARTURE_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	
	@Column(name = "RETURN_DATE")
	@Temporal(TemporalType.DATE)
	private Date returnDate;
	
	@Column(name = "PASSENGER_NUMBER", columnDefinition = "integer")
	private Integer passengerNumber;	

	@Column(name = "CURRENCY", columnDefinition = "varchar(3)")
	private String currency;
	
	@Column(name = "NUMBER_OF_STOPS", columnDefinition = "integer")
	private Integer numberOfStops;
	
	@Column(name = "PRICE", columnDefinition = "decimal(15,2)")
	private BigDecimal price;

	@Column(name = "FLIGHTS_FETCHED", columnDefinition = "integer")
	private Integer flightsFetched;

	

	
	public Integer getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Integer idFlight) {
		this.idFlight = idFlight;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(Integer passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getNumberOfStops() {
		return numberOfStops;
	}

	public void setNumberOfStops(Integer numberOfStops) {
		this.numberOfStops = numberOfStops;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getFlightsFetched() {
		return flightsFetched;
	}

	public void setFlightsFetched(Integer flightsFetched) {
		this.flightsFetched = flightsFetched;
	}



	


	

}
