package com.llit.rest.flights.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchRequestBody {

	private String originLocationCode;
	private String destinationLocationCode;
	private String departureDate;	
	private String returnDate;	
	private String currency;
	private Integer numberOfPassengers;
	private Integer max;
	
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
	public Integer getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(Integer numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}

	
}
