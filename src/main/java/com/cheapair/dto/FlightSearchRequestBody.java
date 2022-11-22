package com.cheapair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FlightSearchRequestBody {

	
    String departureAirport;

    String destinationAirport;

    @JsonFormat(pattern = "dd.MM.yyyy.'T'HH:mm:ss")    
    String departureDate;

    @JsonFormat(pattern = "dd.MM.yyyy.'T'HH:mm:ss")
    String arrivalDate;

    String passengerNumber;

    String currency;

	
	
	
}
