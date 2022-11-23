package com.cheapair;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
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


