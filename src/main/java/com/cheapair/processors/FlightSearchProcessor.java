package com.cheapair.processors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightSearchProcessor {

	@Autowired
	AmedeusClient amadeusClient;
	
	/**
	 * Retrieves amadeus flights
	 * mapped to fronted DTO flights
	 * @param requestBody 
	 * @return 
	 * @throws Exception 
	 */
	public FlightResponseBody retrieveAmadeusFlights(FlightSearchRequestBody requestBody) throws Exception {		

		FlightResponseBody flightResponseBody = new FlightResponseBody();
		
		//TODO param checks 		
	
		
		FlightOfferSearch[] amadeusFlights = amadeusClient.getAmadeusFlights(null, null, null, null, null);
		
		List<FlightOfferSearch> amadeusFlightsList = new ArrayList<>();	
		Collections.addAll(amadeusFlightsList, amadeusFlights);
		
		
		for(FlightOfferSearch flight : amadeusFlightsList) {
			
			FlightAvailable responseFlight = new FlightAvailable();
			

			
			//map amadeus response to dtoFlightsResponse 

		}
		
		 
		
		return new FlightResponseBody();
		
	}
	
	
	
	
	
}
