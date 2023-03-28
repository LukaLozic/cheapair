package com.llit.rest.flights.dto;



import lombok.Data;

@Data
public class FlightAvailable {


	private String idFlight;
	private String originLocationCode;
	private String destinationLocationCode;
	private String departureDate;	
	private String returnDate;	
	private String currency;
	private Integer numberOfStops;
	private String price;
	
	
	public String getIdFlight() {
		return idFlight;
	}
	public void setIdFlight(String idFlight) {
		this.idFlight = idFlight;
	}
	public String getOriginLocationCode() {
		return originLocationCode;
	}
	public void setOriginLocationCode(String originLocationCode) {
		this.originLocationCode = originLocationCode;
	}
	public String getDestinationLocationCode() {
		return destinationLocationCode;
	}
	public void setDestinationLocationCode(String destinationLocationCode) {
		this.destinationLocationCode = destinationLocationCode;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	
	






	
	
	
}
