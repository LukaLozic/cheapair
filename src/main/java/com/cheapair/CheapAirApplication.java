package com.cheapair;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dbmodels.Airport;
import com.cheapair.dbmodels.Flight;
import com.cheapair.mappers.FlightAmadeusToFlightResponseAndDBMapper;
import com.cheapair.repositories.AirportRepository;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CheapAirApplication {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
	
    private static Logger log = LoggerFactory.getLogger(CheapAirApplication.class);
	
	private static FlightRepository flightRepository;
	
	private static AirportRepository airportRepository;
	
	private static FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseMapper;

	
	@Autowired
	public CheapAirApplication(FlightRepository flightRepository, AirportRepository airportRepository, FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseMapper) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.flightAmadeusToFlightResponseMapper = flightAmadeusToFlightResponseMapper;


    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CheapAirApplication.class, args);
		
		List<Airport> airportDeparture = airportRepository.findByCode("ZAG"); 
		List<Airport> airportArrival = airportRepository.findByCode("LRH"); 
		Date dateDeparture = new SimpleDateFormat(DATE_FORMAT).parse("2022-12-14");
		Date dateArrival = new SimpleDateFormat(DATE_FORMAT).parse("2022-12-16");  		

		
		List<Flight> flight = flightRepository.findAirports(airportDeparture.get(0), airportArrival.get(0), dateDeparture, dateArrival, 1, "EUR");

		
		int i = 1 + 2;
	}	
	
	
	 public static Connection connect() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cheapa", "postgres", "nova4sonytv1995");
	            System.out.println("Connected to the PostgreSQL server successfully.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        return conn;
	    }

}


