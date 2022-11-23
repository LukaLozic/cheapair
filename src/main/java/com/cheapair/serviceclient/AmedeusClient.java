package com.cheapair.serviceclient;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class AmedeusClient {

	public static final String AMADEUS_CLIENT_ID = System.getenv("AMADEUS_CLIENT_ID");
	public static final String AMADEUS_CLIENT_SECRET = System.getenv("AMADEUS_CLIENT_SECRET");
		
	
	public FlightOfferSearch[] getAmadeusFlights(String originLocationCode, String destinationLocationCode, 
			String departureDate, String returnDate, Integer numberOfPassengers, Integer max) throws Exception {
		
		Amadeus amadeus = null;
		
		try {
		     amadeus = Amadeus
		            .builder(AMADEUS_CLIENT_ID, AMADEUS_CLIENT_SECRET)
		            .build();

		} catch (Exception e) {
			String errorMessage = "Error building amadeus object using clientId and clientSecret.";
			throw new Exception(errorMessage, e);
			//TODO add logger
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
			throw new Exception(errorMessage, e);
			//TODO add logger
		}
	
		if(amadeusFlights.length == 0) {
			String infoMessage = "There is no flights for given criteria.";
			throw new Exception(infoMessage);
		}
		
		if(amadeusFlights[0].getResponse() != null && amadeusFlights[0].getResponse().getStatusCode() != 200) {
			String errorMessage = "Response status code from Amadeus is " + amadeusFlights[0].getResponse().getStatusCode();
			throw new Exception(errorMessage);
		}

	   return amadeusFlights;
		
	}

}
