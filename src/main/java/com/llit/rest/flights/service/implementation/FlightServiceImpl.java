package com.llit.rest.flights.service.implementation;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amadeus.resources.FlightOfferSearch;
import com.llit.entity.dao.Airport;
import com.llit.entity.dao.Flight;
import com.llit.dto.FlightAvailable;
import com.llit.dto.FlightResponseBody;
import com.llit.dto.FlightSearchRequestBody;
import com.llit.common.exception.FlightsServiceException;
import com.llit.rest.mappers.FlightAmadeusToFlightResponseAndDBMapper;
import com.llit.rest.mappers.FlightDBToFlightResponseMapper;
import com.llit.entity.repository.AirportRepository;
import com.llit.entity.repository.FlightRepository;
import com.llit.serviceclient.AmedeusClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightServiceImpl {
	
	private static Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	public static final String OBJECT_DB = "object_db";
	
	public static final String OBJECT_RESPONSE = "object_response";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
	
	public static final String EUR = "EUR";
	
	public static final String USD = "USD";



	@Autowired
	private AmedeusClient amadeusClient;
	
	@Autowired
	private FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseAndDBMapper;
	
	@Autowired
	private FlightDBToFlightResponseMapper flightDBToFlightResponse;
	
	@Autowired
	private  FlightRepository flightRepository;
	
	@Autowired
	private  AirportRepository airportRepository;
	
	/**
	 * Retrieves amadeus flights
	 * @param requestBody 
	 * @return 
	 * @throws Exception 
	 */
	public FlightResponseBody retrieveAmadeusFlights(FlightSearchRequestBody requestBody) throws Exception {		

		FlightResponseBody flightResponseBody = new FlightResponseBody();
						
		paramsCheck(requestBody);	
		
		Airport departureAirport = airportIATAcheck(requestBody.getOriginLocationCode());
		Airport arrivalAirport = airportIATAcheck(requestBody.getDestinationLocationCode());
		
		List<Flight> existingFlights = existingFlightsInDB(requestBody, departureAirport, arrivalAirport);
				
				
		if(existingFlights == null) {
			
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
			
			int idNum = 1;

			for(FlightOfferSearch flightAmadeus : amadeusFlightsList) {					
												
				HashMap<String, Object> objectMap = flightAmadeusToFlightResponseAndDBMapper.process(flightAmadeus, requestBody, departureAirport, arrivalAirport);		
				
				if(objectMap == null) {
					continue;
				}
				
				FlightAvailable flightAvailableResponse = (FlightAvailable) objectMap.get(OBJECT_RESPONSE);
				flightAvailableResponse.setIdFlight(String.valueOf(idNum));
				idNum++;
				
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
		else {
			
			List<FlightAvailable> flightsResponse = new ArrayList<>();
			
			int idNum = 1;
			for(Flight flightDB : existingFlights) {
				
				FlightAvailable flightAvailableResponse = flightDBToFlightResponse.process(requestBody, flightDB);
				flightAvailableResponse.setIdFlight(String.valueOf(idNum));
				idNum++;
				flightsResponse.add(flightAvailableResponse);
			}
			
			flightResponseBody.setFlightsAvailable(flightsResponse);
		}
		 		
		return flightResponseBody;		
	}

	private List<Flight> existingFlightsInDB(FlightSearchRequestBody requestBody, Airport departureAirport,
			Airport arrivalAirport) throws Exception {
		
		
		String currency = requestBody.getCurrency();
		Integer nubmerOfPassengers = requestBody.getNumberOfPassengers();
		Integer flightsFetched = requestBody.getMax();
		Date departureDate = null;
		Date returnDate = null;
		
		try {
			
			String departureDateStr = requestBody.getDepartureDate();	
			 departureDate = new SimpleDateFormat(DATE_FORMAT).parse(departureDateStr);  		
			
			String returnDateStr = requestBody.getReturnDate();
			 returnDate= new SimpleDateFormat(DATE_FORMAT).parse(returnDateStr);  
		}
		catch (Exception e) {
			
			String errorMessage = "Error checking for existing flights in DB: " + e.getMessage();
			log.error(errorMessage);
			throw new FlightsServiceException(errorMessage, e);
		}
		
		List<Flight> flightList = null;
		flightList = flightRepository.findFlightsByAllParametres(departureAirport, arrivalAirport, departureDate, returnDate, nubmerOfPassengers, currency, flightsFetched);
	
		List<Flight> flightListAllWithoutFlightsFetchedParam = null;
		flightListAllWithoutFlightsFetchedParam = flightRepository.findFlightsBySixParametres(departureAirport, arrivalAirport, departureDate, returnDate, nubmerOfPassengers, currency);
		
		if(flightList != null && flightListAllWithoutFlightsFetchedParam != null) {
			
			if(flightList.size() != 0 &&flightList.size() == flightListAllWithoutFlightsFetchedParam.size()) {
				return flightList;
			}
		}
		else {
			
			return null;
		}		
		
		try {
			
			flightRepository.deleteAll(flightListAllWithoutFlightsFetchedParam);
			return null;
		}
		catch (Exception e) {
			
			String errorMessage = "Error deleting existing flights in DB: " + e.getMessage();
			log.error(errorMessage);
			throw new FlightsServiceException(errorMessage, e);
		}
	}

	private Airport airportIATAcheck(String airportLocation) throws Exception {
		
		Airport airportFound = null;
				
		if(airportLocation.length() > 3) {
			
			List<Airport> airports = airportRepository.findByCityNameContainingIgnoreCase(airportLocation);
			
			if(airports.size() == 0) {
				
				String infoMessage = "There is no airports for given location: " + airportLocation;
				log.info(infoMessage);
				throw new Exception(infoMessage);
			}
			if(airports.size() == 1) {
				
				airportFound = airports.get(0);								
				return airportFound;
			}
			else {
				
				StringBuilder sb = new StringBuilder();
				String infoMessage = "For given location, " + airportLocation + " there is many airport IATA codes. "
						+ "Please pick one from the following values: ";
				
				for(Airport airport : airports) {
					String iataCode = airport.getCode();
					sb.append(iataCode);
					sb.append(" ");					
				}				
				
				infoMessage = infoMessage.concat(sb.toString());
				
				log.info(infoMessage);
				throw new Exception(infoMessage);		
			}
		}
		else {
			
			//There is possibility to fetch two airports by same code, same alt and lon, only different id_airport in db
			//so by default, in that case, first will be taken in account
			List<Airport> airportsFoundByCode = airportRepository.findByCode(airportLocation);
			
			if(airportsFoundByCode == null || airportsFoundByCode.isEmpty()) {
				
				String infoMessage = "There is no airports for given location: " + airportLocation;
				log.info(infoMessage);
				throw new Exception(infoMessage);			
			}
			
			airportFound = airportsFoundByCode.get(0);
			return airportFound;
		}
	}

	@SuppressWarnings("deprecation")
	private void paramsCheck(FlightSearchRequestBody requestBody) throws Exception {

		String infoMessage = null;
		
		if(requestBody == null) {
			
			infoMessage = "Request body is null.";
			throw new Exception(infoMessage);
		}
		
		if(StringUtils.isEmpty(requestBody.getDepartureDate())) {
			
			infoMessage = "Departure date is null or empty.";
			throw new Exception(infoMessage);
		}
		
		if(StringUtils.isEmpty(requestBody.getDepartureDate())) {
			
			infoMessage = "Return date is null or empty.";
			throw new Exception(infoMessage);
		}
		
		if(StringUtils.isEmpty(requestBody.getOriginLocationCode())) {
			
			infoMessage = "Origin location code is null or empty.";
			throw new Exception(infoMessage);
		}

		
		if(StringUtils.isEmpty(requestBody.getDestinationLocationCode())) {
			
			infoMessage = "Destination location code is null or empty.";
			throw new Exception(infoMessage);
		}		
		
		if(requestBody.getNumberOfPassengers() == null) {
			
			infoMessage = "Number of passenger is null or empty.";
			throw new Exception(infoMessage);
		}
		
		if(requestBody.getNumberOfPassengers() > 10) {
			
			infoMessage = "Max Number of passenger is 10.";
			throw new Exception(infoMessage);
		}
		
		if(StringUtils.isEmpty(requestBody.getCurrency())) {
			
			infoMessage = "Currency is null or empty.";
			throw new Exception(infoMessage);
		}
		
		if(!requestBody.getCurrency().equals(EUR)) {
			
			if(!requestBody.getCurrency().equals(USD)) {
				
				infoMessage = "Currency must be "
						+ "" + EUR + " or " + USD;
				throw new Exception(infoMessage);
			}
		}
		
		if(requestBody.getMax() == null || requestBody.getMax().equals(0)) {
			
			infoMessage = "Max flights number is set to null or 0.";
			throw new Exception(infoMessage);
		}
		
		if(requestBody.getMax() > 99) {
			
			infoMessage = "Max flights number is 99.";
			throw new Exception(infoMessage);
		}
	}

	
}
