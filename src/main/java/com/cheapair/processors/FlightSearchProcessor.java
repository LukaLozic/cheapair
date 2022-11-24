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
import com.cheapair.dbmodels.Airport;
import com.cheapair.dbmodels.Flight;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.mappers.FlightAmadeusToFlightResponseMapper;
import com.cheapair.repositories.AirportRepository;
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
	
	private  AirportRepository airportRepository;

	
	@Autowired
	public FlightSearchProcessor(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;

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
		
		Airport departureAirport = airportIATAcheck(requestBody.getOriginLocationCode());
		Airport arrivalAirport = airportIATAcheck(requestBody.getDestinationLocationCode());

		
		boolean existsInDB = false;
		
//TODO check in db if there are entities with corresponding criteria values		
//	   if doesn't exist in db, than fill db object for persisting
				
		if(!existsInDB) {
			FlightOfferSearch[] amadeusFlights = amadeusClient.getAmadeusFlights(
					departureAirport.getCode(),
					arrivalAirport.getCode(), 
					requestBody.getDepartureDate(), 
					requestBody.getReturnDate(),
					requestBody.getNumberOfPassengers(),
					requestBody.getMax());
				
			List<FlightOfferSearch> amadeusFlightsList = new ArrayList<>();	
			Collections.addAll(amadeusFlightsList, amadeusFlights);	
			
			
			List<FlightAvailable> flightsResponse = new ArrayList<>();
			for(FlightOfferSearch flightAmadeus : amadeusFlightsList) {					
				
								
				HashMap<String, Object> objectMap = flightAmadeusToFlightResponseMapper.process(flightAmadeus, requestBody, departureAirport, arrivalAirport);		
				
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

	private Airport airportIATAcheck(String airportLocation) throws Exception {
		
		Airport airportFound = null;
		
		
		if(airportLocation.length() > 3) {
			
			List<Airport> airports = airportRepository.findByCityNameContainingIgnoreCase(airportLocation);
			
			if(airports.size() == 0) {
				String errorMessage = "There is no airports for given location: " + airportLocation;
				throw new Exception(errorMessage);
				//TODO log				
			}
			if(airports.size() == 1) {
				
				airportFound = airports.get(0);								
				return airportFound;
			}
			else {
				StringBuilder sb = new StringBuilder();
				String errorMessage = "For given location, " + airportLocation + " there is many airport IATA codes. "
						+ "Please pick one from the following values: ";
				
				for(Airport airport : airports) {
					String iataCode = airport.getCode();
					sb.append(iataCode);
					sb.append(" ");					
				}								
				throw new Exception(errorMessage.concat(sb.toString()));		
				//TODO log								
			}
		}
		else {
			
			//There is possibility to fetch two airports by same code, same alt and lon, only different id_airport in db
			//so by default, in that case, first will be taken in account
			List<Airport> airportsFoundByCode = airportRepository.findByCode(airportLocation);
			
			if(airportsFoundByCode == null || airportsFoundByCode.isEmpty()) {
				String errorMessage = "There is no airports for given location: " + airportLocation;
				throw new Exception(errorMessage);
				//TODO log		
			}
			
			airportFound = airportsFoundByCode.get(0);
			return airportFound;
		}
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
