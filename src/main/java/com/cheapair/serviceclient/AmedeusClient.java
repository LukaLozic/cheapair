package com.cheapair.serviceclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.exceptions.FlightsServiceException;
import com.cheapair.exceptions.NoFlightsException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class AmedeusClient {
	
	private static Logger log = LoggerFactory.getLogger(AmedeusClient.class);

	public static final String AMADEUS_CLIENT_ID = System.getenv("AMADEUS_CLIENT_ID");
	public static final String AMADEUS_CLIENT_SECRET = System.getenv("AMADEUS_CLIENT_SECRET");
		
	
	public FlightOfferSearch[] getAmadeusFlights(String originLocationCode, String destinationLocationCode, 
			String departureDate, String returnDate, Integer numberOfPassengers, Integer max) throws Exception, NoFlightsException {
		
		Amadeus amadeus = null;
		
		try {
			
		     amadeus = Amadeus
		            .builder(AMADEUS_CLIENT_ID, AMADEUS_CLIENT_SECRET)
		            .build();

		} catch (Exception e) {
			
			String errorMessage = "Error building amadeus object using clientId and clientSecret.";
			log.error(errorMessage);
			throw new Exception(errorMessage, e);
			
		}
	    
		FlightOfferSearch[] amadeusFlights = null;
		
		try {
			   amadeusFlights = amadeus.shopping.flightOffersSearch.get(Params.with("originLocationCode", originLocationCode)
			    		.and("destinationLocationCode", destinationLocationCode)
			    		.and("departureDate", departureDate)
			    		.and("returnDate", returnDate)
			    		.and("adults", numberOfPassengers)
			    		.and("max", max));
			   
		} catch (Exception e) {
			
			String errorMessage = "Error fetching flights from amadeus.";
			throw new FlightsServiceException(errorMessage, e);			
		}
	
		if(amadeusFlights.length == 0) {
			
			String infoMessage = "There is no flights for given criteria.";
			throw new NoFlightsException(infoMessage);
		}
		
		if(amadeusFlights[0].getResponse() != null && amadeusFlights[0].getResponse().getStatusCode() != 200) {
			
			String errorMessage = "Response status code from Amadeus is " + amadeusFlights[0].getResponse().getStatusCode();
			throw new FlightsServiceException(errorMessage, null);
		}

	   return amadeusFlights;		
	}

	

}
