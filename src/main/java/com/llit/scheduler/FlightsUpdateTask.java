package com.llit.scheduler;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.llit.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amadeus.resources.FlightOfferSearch;
import com.llit.entity.dao.FlightEntity;
import com.llit.exception.NoFlightsException;
import com.llit.entity.repository.FlightRepository;
import com.llit.serviceclient.AmedeusClient;

@Service
public class FlightsUpdateTask {
	private static Logger log = LoggerFactory.getLogger(FlightsUpdateTask.class);
	@Autowired
	private AmedeusClient amadeusClient;
	@Autowired
	private  FlightRepository flightRepository;

	public void updateDbFlights() throws Exception {		

		List<FlightEntity> flightsList = null;

		try {
			flightsList = StreamSupport.stream(flightRepository.findAll().spliterator(), false)
			    .collect(Collectors.toList());
		} 
		catch (Exception e) {
			String errorMessage = "Error fetching flights from db while trying to update them, ";
			log.error(errorMessage);
			throw new Exception(errorMessage.concat(e.getMessage()));
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

		for(FlightEntity flight : flightsList) {
/*
			String departureCode = flight.getDepartureAirport().getCode();
			String arrivalCode = flight.getArrivalAirport().getCode();
			String departureDate = dateFormat.format(flight.getDepartureDate());
			String returnDate = dateFormat.format(flight.getReturnDate());
			Integer passengerNUmber = flight.getPassengerNumber();

			FlightOfferSearch[] flightsFromAmadeus = null;

			try {
				flightsFromAmadeus = amadeusClient.getAmadeusFlights(departureCode, arrivalCode, departureDate, returnDate, passengerNUmber, 1);
				updatePriceForFlight(flight, flightsFromAmadeus[0]);
			}
			catch (NoFlightsException e) {

				String infoMessage = "Flight with id: " + flight.getIdFlight() + " deleted from db because "
						+ "offer for it no longer exists.";
				log.info(infoMessage);
				flightRepository.delete(flight);
				return;
			}*/
		}

	}
	
	private void updatePriceForFlight(FlightEntity flightDb, FlightOfferSearch flightAmadeus) throws Exception {
/*
		BigDecimal flightDbPrice = flightDb.getPrice();
		
		try {
			if(flightAmadeus.getPrice() != null) {
				if(flightAmadeus.getPrice().getGrandTotal() != null) {
					
					String flightAmadeusPrice = flightAmadeus.getPrice().getGrandTotal();
					BigDecimal flightAmadeusPriceBigDecimal = new BigDecimal(flightAmadeusPrice);			
				
					if(flightDbPrice.compareTo(flightAmadeusPriceBigDecimal) != 0) {
									
						flightDb.setPrice(flightAmadeusPriceBigDecimal);
						flightRepository.save(flightDb);
					}
				}			
			}
		} catch (Exception e) {
			String errorMessage = "Error updating flights into db, ";
			log.error(errorMessage);
			throw new Exception(errorMessage.concat(e.getMessage()));
		}*/
	}
}
