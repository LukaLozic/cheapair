package com.cheapair.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cheapair.common.EmailServiceImpl;
import com.cheapair.dto.FlightAvailable;
import com.cheapair.dto.FlightResponseBody;
import com.cheapair.dto.FlightSearchRequestBody;
import com.cheapair.exceptions.FlightsServiceException;
import com.cheapair.processors.FlightSearchProcessor;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FlightsController {
	
	@Autowired
	private  FlightSearchProcessor processor;
	
	@Autowired
	private  EmailServiceImpl emailServiceImpl;

		
	private static Logger log = LoggerFactory.getLogger(FlightsController.class);
	

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
		catch(FlightsServiceException fse) {
						
			StringBuilder emailMessage = new StringBuilder();
			emailMessage.append(fse.getMessage());
			
			log.error(fse.getMessage());						
			
			emailServiceImpl.sendMailWithLogFile("Exception occured.", emailMessage.toString());

			model.addAttribute("exception", fse);
			
		    return "searchFlights";			
		}
		catch (Exception e) {
		
			log.error(e.getMessage());		
			model.addAttribute("exception", e);
		    return "searchFlights";
		}	
	}
	
	
	
	

	
	@GetMapping("/test")
	public String test(Model model) {
			
		return "TEST";
	}
	
}
