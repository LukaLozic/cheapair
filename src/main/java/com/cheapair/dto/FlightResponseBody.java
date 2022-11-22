package com.cheapair.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

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
