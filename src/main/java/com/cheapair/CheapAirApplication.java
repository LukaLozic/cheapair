package com.cheapair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.cheapair.dbmodels.Flight;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.serviceclient.AmedeusClient;

@SpringBootApplication
public class CheapAirApplication {
	

	
	
	private static FlightRepository flightRepository;
	
	@Autowired
	public CheapAirApplication(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CheapAirApplication.class, args);
		
		FlightOfferSearch[] amadeusFlights = null;
		
		try {
			Amadeus amadeus = null;
			
			try {
			     amadeus = Amadeus
			            .builder("WjYR0UsA0A1AJzOxTCWN3dlnBG43rG9G", "qOHwk98y6GWZQvf5")
			            .build();

			} catch (Exception e) {
				String errorMessage = "Error building amadeus object using clientId and clientSecret.";
				throw new Exception(errorMessage, e);
				//TODO add logger
			}
			
			
			try {
				   amadeusFlights = amadeus.shopping.flightOffersSearch.get(Params.with("originLocationCode", "SYD")
				    		.and("destinationLocationCode", "BKK")
				    		.and("departureDate", "2022-12-12")
				    		.and("returnDate", "2022-12-14")
				    		.and("adults", 1)
				    		.and("max", 10));
				   
				   int i = 1 + 2;

			} catch (Exception e) {
				String errorMessage = "Error fetching flights from amadeus.";
				throw new Exception(errorMessage, e);
				//TODO add logger
			}
			   
		} catch (Exception e) {
			String errorMessage = "Error fetching flights from amadeus.";
			throw new Exception(errorMessage, e);
			//TODO add logger
		}
	
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


