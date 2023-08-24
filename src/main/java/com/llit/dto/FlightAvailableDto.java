package com.llit.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightAvailableDto {
	private String idFlight;
	private String originLocationCode;
	private String destinationLocationCode;
	private String departureDate;	
	private String returnDate;	
	private String currency;
	private Integer numberOfStops;
	private String price;
}
