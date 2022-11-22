package com.cheapair.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FlightSearchRequestBody {


	private String originLocationCode;
	private String destinationLocationCode;
	private Date departureDate;	
	private Date returnDate;	
	private Integer passengerNumber;	
	private String currency;
	private String numberOfPassengers;
	private Integer max;
	
	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(String numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	public Integer getMaxNumber() {
		return max;
	}
	public void setMaxNumber(Integer maxNumber) {
		this.max = maxNumber;
	}
	public String getDepartureAirport() {
		return originLocationCode;
	}
	public void setDepartureAirport(String departureAirport) {
		this.originLocationCode = departureAirport;
	}
	public String getArrivalAirport() {
		return destinationLocationCode;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.destinationLocationCode = arrivalAirport;
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

	
	
	
}
