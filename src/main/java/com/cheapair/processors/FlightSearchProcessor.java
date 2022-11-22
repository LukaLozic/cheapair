package com.cheapair.processors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightSearchProcessor {

	@Autowired
	private AmedeusClient amadeusClient;
	
	private static FlightRepository flightRepository;
	
	@Autowired
	public FlightSearchProcessor(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
	
	/**
	 * Retrieves amadeus flights
	 * mapped to fronted DTO flights
	 * @param requestBody 
	 * @return 
	 * @throws Exception 
	 */
	public FlightResponseBody retrieveAmadeusFlights(FlightSearchRequestBody requestBody) throws Exception {		

		FlightResponseBody flightResponseBody = new FlightResponseBody();
						
		paramsCheck(requestBody);	
		
		
		FlightOfferSearch[] amadeusFlights = amadeusClient.getAmadeusFlights(
				requestBody.getDepartureAirport(), 
				requestBody.getArrivalAirport(), 
				requestBody.getDepartureDate(), 
				requestBody.getPassengerNumber(), 
				requestBody.getMaxNumber());
		
		
		List<FlightOfferSearch> amadeusFlightsList = new ArrayList<>();	
		Collections.addAll(amadeusFlightsList, amadeusFlights);
		
		
		for(FlightOfferSearch flightAmadeus : amadeusFlightsList) {
			
			FlightAvailable responseFlight = new FlightAvailable();
			
			
			
			//map amadeus response to dtoFlightsResponse 

		}
		
		 
		
		return new FlightResponseBody();
		
	}

	@SuppressWarnings("deprecation")
	private void paramsCheck(FlightSearchRequestBody requestBody) throws Exception {


		if(requestBody == null) {
			throw new Exception("Request body is null.");
		}
		
		if(StringUtils.isEmpty(requestBody.getDepartureAirport())) {
			throw new Exception("Departure airport is null or empty.");
		}
		
		if(StringUtils.isEmpty(requestBody.getArrivalAirport())) {
			throw new Exception("Arrival airport is null or empty.");
		}
		
		if(requestBody.getDepartureDate() == null) {
			throw new Exception("Departure date is null or empty.");
		}
		
		if(requestBody.getPassengerNumber() == null) {
			throw new Exception("Passenger number is null or empty.");
		}
	}
	
	
	
	
	
}
