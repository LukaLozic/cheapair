package com.cheapair;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cheapair.common.EmailServiceImpl;
import com.cheapair.mappers.FlightAmadeusToFlightResponseAndDBMapper;
import com.cheapair.repositories.AirportRepository;
import com.cheapair.repositories.FlightRepository;
import com.cheapair.scheduledtasks.FlightsUpdateTask;
import com.cheapair.serviceclient.AmedeusClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class CheapAirApplication {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
	
    private static Logger log = LoggerFactory.getLogger(CheapAirApplication.class);
	
	private static FlightRepository flightRepository;
	
	private static AirportRepository airportRepository;
	
	private static FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseMapper;
	
	private static AmedeusClient amedeusClient;
	
	private static FlightsUpdateTask flightNewPricesSearchTask;
	
	private static EmailServiceImpl emailServiceImpl;
	
    private static JavaMailSender emailSender;

	
	@Autowired
	public CheapAirApplication(FlightRepository flightRepository, AirportRepository airportRepository, 
			FlightAmadeusToFlightResponseAndDBMapper flightAmadeusToFlightResponseMapper, 
			AmedeusClient amedeusClient, FlightsUpdateTask flightNewPricesSearchTask,
			EmailServiceImpl emailServiceImpl, JavaMailSender emailSender) {
		
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.flightAmadeusToFlightResponseMapper = flightAmadeusToFlightResponseMapper;
        this.amedeusClient = amedeusClient;
        this.flightNewPricesSearchTask = flightNewPricesSearchTask;
        this.emailServiceImpl = emailServiceImpl;
        this.emailSender = emailSender;
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CheapAirApplication.class, args);
		

		}	
	


}


