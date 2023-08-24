package com.llit.dto;

import lombok.Data;

@Data
public class FlightSearchRequestBody {
	private String originLocationCode;
	private String destinationLocationCode;
	private String departureDate;	
	private String returnDate;	
	private String currency;
	private Integer numberOfPassengers;
	private Integer max;
}
