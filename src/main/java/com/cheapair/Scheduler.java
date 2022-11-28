package com.cheapair;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheapair.scheduledtasks.FlightsUpdateTask;

@Component
public class Scheduler {

	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	
	@Autowired
	FlightsUpdateTask flightsUpdateTask;

	//@Scheduled(fixedRate = 10000)
	public void updateStoredFlights() {
				
		log.info("Started update of existing flights data in DB, {}", dateFormat.format(new Date()));
		
		try {
			
			flightsUpdateTask.updateDbFlights();
			
			log.info("Finished update of existing flights data in DB, {}", dateFormat.format(new Date()));			
		} 
		catch (Exception e) {
			
			String errorMessage = "Error updating DB flights: ";
			log.error(errorMessage + e.getMessage());
		}
		
		
		
		
	}
}