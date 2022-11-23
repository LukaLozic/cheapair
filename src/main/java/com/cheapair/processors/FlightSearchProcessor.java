package com.cheapair.processors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dbmodels.Flight;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.mappers.FlightAmadeusToFlightResponseMapper;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightSearchProcessor {
	
	public static final String OBJECT_DB = "object_db";
	
	public static final String OBJECT_RESPONSE = "object_response";

	@Autowired
	private AmedeusClient amadeusClient;
	
	@Autowired
	private FlightAmadeusToFlightResponseMapper flightAmadeusToFlightResponseMapper;
	
	private  FlightRepository flightRepository;
	
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
		
		boolean existsInDB = false;
		
//TODO check in db if there are entities with corresponding criteria values		
//	   if doesn't exist in db, than fill db object for persisting
				
		if(!existsInDB) {
			FlightOfferSearch[] amadeusFlights = amadeusClient.getAmadeusFlights(
					requestBody.getOriginLocationCode(),
					requestBody.getDestinationLocationCode(), 
					requestBody.getDepartureDate(), 
					requestBody.getReturnDate(),
					requestBody.getNumberOfPassengers(),
					requestBody.getMax());
				
			List<FlightOfferSearch> amadeusFlightsList = new ArrayList<>();	
			Collections.addAll(amadeusFlightsList, amadeusFlights);	
			
			
			List<FlightAvailable> flightsResponse = new ArrayList<>();
			for(FlightOfferSearch flightAmadeus : amadeusFlightsList) {					
				
				
				
				HashMap<String, Object> objectMap = flightAmadeusToFlightResponseMapper.process(flightAmadeus, requestBody);		
				
				FlightAvailable flightAvailableResponse = (FlightAvailable) objectMap.get(OBJECT_RESPONSE);
				if(flightAvailableResponse != null) {
					flightsResponse.add(flightAvailableResponse);
				}		
				
				Flight flightDb = (Flight) objectMap.get(OBJECT_DB);
				if(flightDb != null) {
					flightRepository.save(flightDb);
				}				
			}			
			flightResponseBody.setFlightsAvailable(flightsResponse);
		}
		 
		
		return flightResponseBody;
		
	}

	@SuppressWarnings("deprecation")
	private void paramsCheck(FlightSearchRequestBody requestBody) throws Exception {


		if(requestBody == null) {
			throw new Exception("Request body is null.");
		}
		
		if(StringUtils.isEmpty(requestBody.getOriginLocationCode())) {
			throw new Exception("Origin location code is null or empty.");
		}
		
		if(StringUtils.isEmpty(requestBody.getDestinationLocationCode())) {
			throw new Exception("Destination location code is null or empty.");
		}
		
		if(StringUtils.isEmpty(requestBody.getDepartureDate())) {
			throw new Exception("Departure date is null or empty.");
		}
		
		if(StringUtils.isEmpty(requestBody.getDepartureDate())) {
			throw new Exception("Return date is null or empty.");
		}
		
		if(requestBody.getNumberOfPassengers() == null) {
			throw new Exception("Number of passenger is null or empty.");
		}
		
		if(StringUtils.isEmpty(requestBody.getCurrency())) {
			throw new Exception("Currency is null or empty.");
		}
	}
	
	
	
	
	
}
