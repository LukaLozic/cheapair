package com.cheapair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.processors.FlightSearchProcessor;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/flights")
public class FlightsController {
	
	@Autowired
	FlightSearchProcessor processor;
	

	@PostMapping("/search")
	public ResponseEntity<Object> getFlights(@RequestBody FlightSearchRequestBody requestBody) {
		
		FlightResponseBody flightResponseBody = null;
		
		try {
			flightResponseBody = processor.retrieveAmadeusFlights(requestBody);
			
		} catch (Exception e) {
				//TODO handle exception			
		}
       
        return ResponseEntity.ok(flightResponseBody);
	}
	
	
	@PostMapping("/update")
	public String updateFlights(@RequestBody FlightSearchRequestBody requestBody) {
			
		return null;

	}
	
	@GetMapping("/test")
	public String test(Model model) {
			
		return "TEST";
	}
	
}
