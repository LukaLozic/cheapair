package com.cheapair;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dbmodels.Airport;
import com.cheapair.dbmodels.Flight;
import com.cheapair.repositories.AirportRepository;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.serviceclient.AmedeusClient;

@SpringBootApplication
public class CheapAirApplication {
	

	
	
	private static FlightRepository flightRepository;
	
	private static AirportRepository airportRepository;

	
	@Autowired
	public CheapAirApplication(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;

    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CheapAirApplication.class, args);
		
		Iterable<Flight> flights = flightRepository.findAll();
				
		List<Airport> airports = airportRepository.findByCityNameContainingIgnoreCase("zagreb");
		
		List<Airport> airport = airportRepository.findByCode("AAH");
		
		int size = airports.size();
		
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


