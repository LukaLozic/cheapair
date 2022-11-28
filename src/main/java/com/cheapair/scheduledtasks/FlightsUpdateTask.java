package com.cheapair.scheduledtasks;

import static com.cheapair.common.Helper.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dbmodels.Flight;
import com.cheapair.exceptions.NoFlightsException;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightsUpdateTask {
	
	private static Logger log = LoggerFactory.getLogger(FlightsUpdateTask.class);
	
	public static final String OBJECT_DB = "object_db";
	
	public static final String OBJECT_RESPONSE = "object_response";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";

	@Autowired
	private AmedeusClient amadeusClient;
	
	@Autowired
	private  FlightRepository flightRepository;
	
	
	/**
	 * Updates db flights
	 * @param requestBody 
	 * @return 
	 * @throws Exception 
	 */
	public void updateDbFlights() throws Exception {		

		List<Flight> flightsList = null;
		
		try {
			
			Iterable<Flight> flights = flightRepository.findAll();
			
			flightsList = StreamSupport.stream(flights.spliterator(), false)
			    .collect(Collectors.toList());
			
		} 
		catch (Exception e) {
			
			String errorMessage = "Error fetching flights from db while trying to update them, ";
			log.error(errorMessage);
			throw new Exception(errorMessage.concat(e.getMessage()));
		}
		
		 
		 
		for(Flight flight : flightsList) {
			 
			 String departureCode = flight.getDepartureAirport().getCode();
			 String arrivalCode = flight.getArrivalAirport().getCode();
			 String departureDate = convertDateToString(flight.getDepartureDate(), DATE_FORMAT);
			 String returnDate = convertDateToString(flight.getReturnDate(), DATE_FORMAT);
			 Integer passengerNUmber = flight.getPassengerNumber();

			 FlightOfferSearch[] flightsFromAmadeus = null;
			 
			 try {
				
				 flightsFromAmadeus = amadeusClient.getAmadeusFlights(departureCode, arrivalCode, departureDate, returnDate, passengerNUmber, 1);
			 } 
			 
			 //delete flight in db if flight is no longer available			 
			 catch (NoFlightsException e) {
				 
				 String infoMessage = "Flight with id: " + flight.getIdFlight() + " deleted from db because "
				 		+ "offer for it no longer exists.";
				 log.info(infoMessage);
				 flightRepository.delete(flight);
				 continue;
			 }
			 
			 
			 if(flightsFromAmadeus.length != 0) {
				 
				 updatePriceForFlight(flight, flightsFromAmadeus[0]);
			 }
			 			
		 }		 
	}
	
	private void updatePriceForFlight(Flight flightDb, FlightOfferSearch flightAmadeus) throws Exception {
		
		BigDecimal flightDbPrice = flightDb.getPrice();
		
		try {
			
			if(flightAmadeus.getPrice() != null) {
				
				if(flightAmadeus.getPrice().getGrandTotal() != null) {
					
					String flightAmadeusPrice = flightAmadeus.getPrice().getGrandTotal();
					BigDecimal flightAmadeusPriceBigDecimal = new BigDecimal(flightAmadeusPrice);			
				
					if(flightDbPrice.compareTo(flightAmadeusPriceBigDecimal) != 0) {
									
						flightDb.setPrice(flightAmadeusPriceBigDecimal);
						
						flightRepository.save(flightDb);
						
						log.info("Flight with id: " + flightDb.getIdFlight() + " updated in db.");
					}
				}			
			}
		} catch (Exception e) {
			
			String errorMessage = "Error updating flights into db, ";
			log.error(errorMessage);
			throw new Exception(errorMessage.concat(e.getMessage()));
		}
	
	}
}
