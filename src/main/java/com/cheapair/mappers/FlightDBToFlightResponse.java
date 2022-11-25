package com.cheapair.mappers;

import static com.cheapair.common.Helper.*;

import org.springframework.stereotype.Service;


import com.cheapair.dbmodels.Flight;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightSearchRequestBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightDBToFlightResponse {
	

	
		
	public FlightAvailable process(FlightSearchRequestBody requestBody, Flight flightDb) throws Exception {		

			
		FlightAvailable flightResponse = new FlightAvailable();
		
		
		flightResponse.setOriginLocationCode(flightDb.getDepartureAirport().getCode());
		
		flightResponse.setCurrency(flightDb.getCurrency());
		
		flightResponse.setDestinationLocationCode(flightDb.getArrivalAirport().getCode());
		
		flightResponse.setDepartureDate(flightDb.getDepartureDate().toString());
		
		flightResponse.setReturnDate(flightDb.getReturnDate().toString());
		
		flightResponse.setNumberOfStops(flightDb.getNumberOfStops());
		
		flightResponse.setPrice(formatCurrencyValueToCroatianStandard(flightDb.getPrice().toString()));
	
		return flightResponse;
	}
}
