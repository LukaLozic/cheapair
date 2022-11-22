package com.cheapair.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightUpdateProcessor {

	@Autowired
	AmedeusClient amadeusClient;
	
	/**
	 * Retrieves amadeus flights
	 * mapped to fronted DTO flights
	 * @param requestBody 
	 * @throws Exception 
	 */
	public void retrieveAmadeusFlights(FlightSearchRequestBody requestBody) throws Exception {		

		//param checks 
		
		
		amadeusClient.getAmadeusFlights(null, null, null, null, null);
		
		
		//map amadeus response to dtoFlightsResponse 
		
		
		
	}
	
	
	
	
	
}
