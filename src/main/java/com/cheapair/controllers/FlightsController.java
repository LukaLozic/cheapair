package com.cheapair.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cheapair.CheapAirApplication;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.processors.FlightSearchProcessor;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FlightsController {
	
	@Autowired
	FlightSearchProcessor processor;
		
	private static Logger log = LoggerFactory.getLogger(CheapAirApplication.class);
	

    @GetMapping("/main")
    public String showMainMenu(FlightSearchRequestBody requestBody) {
        return "searchFlights";
    }
	
	
	@PostMapping("/searchFlights")
	public String getFlights(FlightSearchRequestBody flightSearchRequestBody, BindingResult result, Model model) {
		
		FlightResponseBody flightResponseBody = null;
		
		try {
			
			flightResponseBody = processor.retrieveAmadeusFlights(flightSearchRequestBody);
			List<FlightAvailable> flights = flightResponseBody.getFlightsAvailable();
			
			model.addAttribute("flights", flights);

		    return "searchFlights";			
		} 
		catch (Exception e) {
		
			log.error(e.getMessage());
			model.addAttribute("exception", e);
		    return "searchFlights";
		}	
	}
	
	
	@GetMapping("/index")
	public String showUserList(Model model) {
	    try {
			model.addAttribute("users", processor.retrieveAmadeusFlights(null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			e.printStackTrace();
		}
	    return "index";
	}
	
	

	
	@GetMapping("/test")
	public String test(Model model) {
			
		return "TEST";
	}
	
}
