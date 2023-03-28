package com.llit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.llit.common.EmailServiceImpl;
import com.llit.entity.dao.Airport;
import com.llit.entity.dao.Flight;
import com.llit.dto.FlightSearchRequestBody;
import com.llit.common.exception.FlightsServiceException;
import com.llit.rest.mappers.FlightAmadeusToFlightResponseAndDBMapper;
import com.llit.entity.repository.AirportRepository;
import com.llit.entity.repository.FlightRepository;
import com.llit.scheduler.FlightsUpdateTask;
import com.llit.serviceclient.AmedeusClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class CheapAirApplication {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
	
    private static Logger log = LoggerFactory.getLogger(CheapAirApplication.class);
	
	private static FlightRepository flightRepository;
	
	private static AirportRepository airportRepository;
	
	private static FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseMapper;
	
	private static AmedeusClient amedeusClient;
	
	private static FlightsUpdateTask flightNewPricesSearchTask;
	
	private static EmailServiceImpl emailServiceImpl;
	
    private static JavaMailSender emailSender;

	
	@Autowired
	public CheapAirApplication(FlightRepository flightRepository, AirportRepository airportRepository, 
			FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseMapper, 
			AmedeusClient amedeusClient, FlightsUpdateTask flightNewPricesSearchTask,
			EmailServiceImpl emailServiceImpl, JavaMailSender emailSender) {
		
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.flightAmadeusToFlightResponseMapper = flightAmadeusToFlightResponseMapper;
        this.amedeusClient = amedeusClient;
        this.flightNewPricesSearchTask = flightNewPricesSearchTask;
        this.emailServiceImpl = emailServiceImpl;
        this.emailSender = emailSender;
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CheapAirApplication.class, args);
		
		List<Airport> airportDeparture = airportRepository.findByCode("JFK");
		List<Airport> airportArrival = airportRepository.findByCode("LRH");
		
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");      

		 String date_string = "2023-01-03";
	     Date date = formatter.parse(date_string);      
	     
		 String date_string2 = "2023-01-26";
	     Date date2 = formatter.parse(date_string2);  
	     
	       
		List<Flight> flightList = flightRepository.findFlightsByAllParametres(airportDeparture.get(0), airportArrival.get(0), date, date2, 1, "USD", 7);
		
		

		
		
		int i = 1 + 2;
		
	}	
	
	private static Airport airportIATAcheck(String airportLocation) throws Exception {
		
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
	
	private static List<Flight> existingFlightsInDB(FlightSearchRequestBody requestBody, Airport departureAirport,
			Airport arrivalAirport) throws Exception {

		List<Flight> flightList = null;
		
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
		
		flightList = flightRepository.findFlightsByAllParametres(departureAirport, arrivalAirport, departureDate, returnDate, nubmerOfPassengers, currency, flightsFetched);
	
		if(flightList == null || flightList.size() == 0) {
			
			return null;
		}
		
		return flightList;
	}

}


