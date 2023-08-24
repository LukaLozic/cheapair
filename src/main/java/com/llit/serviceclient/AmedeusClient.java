package com.llit.serviceclient;


import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AmedeusClient {
	
	//private static final Logger log = LoggerFactory.getLogger(AmedeusClient.class);
	@Value("${amadeus.clientId}")
	private String clientId;
	@Value("${amadeus.clientSecret}")
	private String clientSecret;

	public FlightOfferSearch[] getAmadeusFlights(String originLocationCode, String destinationLocationCode, 
			String departureDate, String returnDate, Integer numberOfPassengers, Integer max) throws Exception {

	   return createAmadeusClient(clientId, clientSecret).shopping.flightOffersSearch
				.get(Params.with("originLocationCode", originLocationCode)
				.and("destinationLocationCode", destinationLocationCode)
				.and("departureDate", departureDate)
				.and("returnDate", returnDate)
				.and("adults", numberOfPassengers)
				.and("max", max));
	}

	private Amadeus createAmadeusClient(String clientId, String clientSecret) throws Exception {
		return Amadeus.builder(clientId, clientSecret).build();
	}
}
