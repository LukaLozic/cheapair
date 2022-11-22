package com.cheapair.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
public class FlightAvailable {



	private String idFlight;
	
	private String departureAirport;
	
	private String arrivalAirport;
	
	private Date departureDate;
	
	private Date returnDate;
	
	private Integer passengerNumber;
	
	private String currency;
	
	private Integer numberOfStops;
	
	public String getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(String idFlight) {
		this.idFlight = idFlight;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
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

	private BigDecimal price;



	
	
	
}
