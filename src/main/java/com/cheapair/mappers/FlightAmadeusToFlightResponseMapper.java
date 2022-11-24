package com.cheapair.mappers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOfferSearch.Itinerary;
import com.amadeus.resources.FlightOfferSearch.SearchPrice;
import com.amadeus.resources.FlightOfferSearch.SearchSegment;
import com.cheapair.dbmodels.Airport;
import com.cheapair.dbmodels.Flight;
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
public class FlightAmadeusToFlightResponseMapper {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String OBJECT_DB = "object_db";
	
	public static final String OBJECT_RESPONSE = "object_response";	

		
	public HashMap<String, Object> process(FlightOfferSearch flightAmadeus, FlightSearchRequestBody requestBody, Airport departureAirport, Airport arrivalAirport) throws Exception {		

		HashMap<String, Object> objectMap = new HashMap<>();
				
		FlightAvailable flightResponse = new FlightAvailable();
		Flight flightDb = new Flight();
		
		
		if(requestBody.getDestinationLocationCode() != null) {
			
			flightResponse.setDestinationLocationCode(arrivalAirport.getCode());
			flightDb.setArrivalAirport(arrivalAirport);
		}
		
		if(requestBody.getCurrency() != null) {
			
			flightResponse.setCurrency(requestBody.getCurrency());
			flightDb.setCurrency(requestBody.getCurrency());
		}
		
		if(requestBody.getOriginLocationCode() != null) {
			
			flightResponse.setOriginLocationCode(departureAirport.getCode());
			flightDb.setDepartureAirport(departureAirport);
		}
		
		if(requestBody.getDepartureDate() != null) {
			
			flightResponse.setDepartureDate(requestBody.getDepartureDate());
			
		    Date date = new SimpleDateFormat(DATE_FORMAT).parse(requestBody.getDepartureDate());  
			flightDb.setDepartureDate(date);
		}
		
		if(requestBody.getReturnDate() != null) {
			
			flightResponse.setReturnDate(requestBody.getReturnDate());
			
		    Date date = new SimpleDateFormat(DATE_FORMAT).parse(requestBody.getReturnDate());  
			flightDb.setReturnDate(date);
		}
		
		
		Itinerary[] itineraryArray = flightAmadeus.getItineraries(); 
		
		if(itineraryArray.length == 0) {
			String infoMessage = "There is no flights for given offer.";
//TODO logg
			return null;
		}
		
		List<Itinerary> itineraryList = new ArrayList<>();	
		Collections.addAll(itineraryList, itineraryArray);
		
//For now only interested in departure info		
		Itinerary itineraryDeparture = itineraryList.get(0);
		
		SearchSegment[] segmentArray = itineraryDeparture.getSegments();
		
		int segments = segmentArray.length;
		if(segmentArray.length == 0) {
			String infoMessage = "There is no segments for given flight.";
			//TODO logg
			return null;
		}
		
		Integer numberOfStops = segments - 1;
		flightResponse.setNumberOfStops(numberOfStops);
		flightDb.setNumberOfStops(numberOfStops);
		
		
		
		List<SearchSegment> segmentList = new ArrayList<>();	
		Collections.addAll(segmentList, segmentArray);
		
						
		SearchPrice amadeusPrice = flightAmadeus.getPrice();
		if(amadeusPrice != null) {
			
			if(amadeusPrice.getGrandTotal() != null) {
				
				BigDecimal grandTotal = new BigDecimal(amadeusPrice.getGrandTotal());
				
				flightResponse.setPrice(grandTotal);
				flightDb.setPrice(grandTotal);
			}
		}
		
		flightDb.setPassengerNumber(requestBody.getNumberOfPassengers());
			


		if(flightAmadeus.getId() != null) {
						
			flightResponse.setIdFlight(flightAmadeus.getId());
//			flightDb.setIdFlight(flightAmadeus.getId());
		}
		
		if(flightResponse != null) {
			objectMap.put(OBJECT_RESPONSE, flightResponse);
		}
		if(flightDb != null) {
			objectMap.put(OBJECT_DB, flightDb);
		}
		
		return objectMap;
		
	}


	
	
	
	
	
}
