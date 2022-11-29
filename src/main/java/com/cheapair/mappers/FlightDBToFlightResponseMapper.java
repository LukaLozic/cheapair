package com.cheapair.mappers;

import static com.cheapair.common.Helper.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.cheapair.dbmodels.Flight;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.exceptions.FlightsServiceException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightDBToFlightResponseMapper {
	
	private static Logger log = LoggerFactory.getLogger(FlightDBToFlightResponseMapper.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
	
		
	public FlightAvailable process(FlightSearchRequestBody requestBody, Flight flightDb) throws Exception {		
			
		FlightAvailable flightResponse = new FlightAvailable();		
		
		flightResponse.setOriginLocationCode(flightDb.getDepartureAirport().getCode());
		
		flightResponse.setCurrency(flightDb.getCurrency());
		
		flightResponse.setDestinationLocationCode(flightDb.getArrivalAirport().getCode());
		
		flightResponse.setDepartureDate(formatDateToCroStandard(flightDb.getDepartureDate().toString()));
		
		flightResponse.setReturnDate(formatDateToCroStandard(flightDb.getReturnDate().toString()));
		
		flightResponse.setNumberOfStops(flightDb.getNumberOfStops());
		
		flightResponse.setPrice(formatCurrencyValueToCroatianStandard(flightDb.getPrice().toString()));
			
		return flightResponse;
	}
	
	private String formatDateToCroStandard(String dateString) throws FlightsServiceException{
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Date date = formatter.parse(dateString);
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_CRO);			    
			String dateFormatedCro = dateFormat.format(date);
			return dateFormatedCro;	
		} 
		catch (Exception e) {
		
			String errorMessage = "Error converting date to croatian format, " + e.getMessage();
			log.error(errorMessage);
			throw new FlightsServiceException(errorMessage, e);
		}

			
	}
	
	
	
}
