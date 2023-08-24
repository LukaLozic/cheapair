package com.llit;

import com.llit.dto.FlightSearchRequestBody;
import com.llit.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlightsController {
	private static Logger log = LoggerFactory.getLogger(FlightsController.class);
	@Autowired
	private FlightService flightService;

    @GetMapping("/main")
    public String showMainMenu(FlightSearchRequestBody requestBody) {
		System.out.println("WORKSSSSS");
		return "searchFlights";
    }
	
	@PostMapping("/searchFlights")
	public String getFlights(FlightSearchRequestBody flightSearchRequestBody, BindingResult result, Model model) throws Exception {
		model.addAttribute("flights", flightService.retrieveAmadeusFlights(flightSearchRequestBody));
		return "searchFlights";
	}
	
	@GetMapping("/test")
	public String test(Model model) { return "TEST"; }
}
