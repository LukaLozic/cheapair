package com.cheapair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;

@SpringBootApplication
public class CheapAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheapAirApplication.class, args);
		
	
		
	}

}
