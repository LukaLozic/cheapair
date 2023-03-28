package com.llit.rest.mappers;

import static com.llit.common.Helper.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOfferSearch.Itinerary;
import com.amadeus.resources.FlightOfferSearch.SearchPrice;
import com.amadeus.resources.FlightOfferSearch.SearchSegment;
import com.llit.entity.dao.Airport;
import com.llit.entity.dao.Flight;
import com.llit.dto.FlightAvailable;
import com.llit.dto.FlightSearchRequestBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightAmadeusToFlightResponseAndDBMapper {
	
	private static Logger log = LoggerFactory.getLogger(FlightAmadeusToFlightResponseAndDBMapper.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
	
	private static final String OBJECT_DB = "object_db";
	
	private static final String OBJECT_RESPONSE = "object_response";	
	
		
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
			
			Date date = new SimpleDateFormat(DATE_FORMAT).parse(requestBody.getDepartureDate());  		
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_CRO);			    
			String dateFormatedCro = dateFormat.format(date);			
			
			flightResponse.setDepartureDate(dateFormatedCro);					   
			flightDb.setDepartureDate(date);
		}
		
		if(requestBody.getReturnDate() != null) {
			
			Date date = new SimpleDateFormat(DATE_FORMAT).parse(requestBody.getReturnDate());  		
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_CRO);			    
			String dateFormatedCro = dateFormat.format(date);
			
			flightResponse.setReturnDate(dateFormatedCro);			
			flightDb.setReturnDate(date);
		}
		
		if(requestBody.getMax() != null) {
						
			flightDb.setFlightsFetched(requestBody.getMax());
		}
		
		
		Itinerary[] itineraryArray = flightAmadeus.getItineraries(); 
		
		if(itineraryArray.length == 0) {
			
			String infoMessage = "There is no flights for given offer.";
			log.info(infoMessage);
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
			log.info(infoMessage);
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
				String grandTotalCroFormat = formatCurrencyValueToCroatianStandard(grandTotal.toString());
			
				flightDb.setPrice(grandTotal);
				flightResponse.setPrice(grandTotalCroFormat);
			}
		}
		
		flightDb.setPassengerNumber(requestBody.getNumberOfPassengers());			

		
		if(flightResponse != null) {
			
			objectMap.put(OBJECT_RESPONSE, flightResponse);
		}
		
		if(flightDb != null) {
			
			objectMap.put(OBJECT_DB, flightDb);
		}
		
		return objectMap;
		
	}

	
}
