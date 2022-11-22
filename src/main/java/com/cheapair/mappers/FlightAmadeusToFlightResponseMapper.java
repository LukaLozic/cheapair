package com.cheapair.mappers;

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
public class FlightAmadeusToFlightResponseMapper {

	

		
	public FlightResponseBody process(FlightOfferSearch flightAmadeus) throws Exception {		

		FlightAvailable flightResponse = new FlightAvailable();
		
		
		
		flightResponse.setArrivalAirport(null);
		
		flightResponse.setCurrency(null);
		
		flightResponse.setDepartureAirport(null);
		
		flightResponse.setDepartureDate(null);
		
		flightResponse.setIdFlight(null);
		
		flightResponse.setNumberOfStops(null);
		
		flightResponse.setPassengerNumber(null);
		
		flightResponse.setPrice(null);
		
		flightResponse.setReturnDate(null);
		
		return null;
		
	}


	
	
	
	
	
}
