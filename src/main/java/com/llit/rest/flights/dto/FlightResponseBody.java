package com.llit.rest.flights.dto;

import lombok.Data;

import java.util.List;

@Data
public class FlightResponseBody {

	List<FlightAvailable> flightsAvailable;
	
	
	public List<FlightAvailable> getFlightsAvailable() {
		return flightsAvailable;
	}

	public void setFlightsAvailable(List<FlightAvailable> flightsAvailable) {
		this.flightsAvailable = flightsAvailable;
	}

	


	
	
	
}
