package com.llit.service.implementation;


import com.amadeus.resources.FlightOfferSearch;
import com.llit.dto.FlightAvailableDto;
import com.llit.dto.FlightSearchRequestBody;
import com.llit.entity.dao.AirportEntity;
import com.llit.entity.dao.FlightEntity;
import com.llit.entity.repository.AirportRepository;
import com.llit.entity.repository.FlightRepository;
import com.llit.mapper.FlightMapper;
import com.llit.service.FlightService;
import com.llit.serviceclient.AmedeusClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.llit.common.Constants.DATE_FORMAT;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl  {
    private static Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);
    @Autowired
    private AmedeusClient amadeusClient;
    @Autowired
    private FlightMapper flightMapper;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;
/*
    public List<FlightAvailableDto> retrieveAmadeusFlights(FlightSearchRequestBody requestBody) throws Exception {

        AirportEntity departureAirport = airportIATAcheck(requestBody.getOriginLocationCode());
        AirportEntity arrivalAirport = airportIATAcheck(requestBody.getDestinationLocationCode());

        List<FlightEntity> existingFlights = existingFlightsInDB(requestBody, departureAirport, arrivalAirport);

        if (existingFlights == null) {

            FlightOfferSearch[] amadeusFlights = amadeusClient.getAmadeusFlights(
                    departureAirport.getCode(),
                    arrivalAirport.getCode(),
                    requestBody.getDepartureDate(),
                    requestBody.getReturnDate(),
                    requestBody.getNumberOfPassengers(),
                    requestBody.getMax());

            List<FlightOfferSearch> amadeusFlightsList = new ArrayList<>(List.of(amadeusFlights));

            flightRepository.saveAll(
                    flightMapper.flightOfferSearchListToFlightEntityList(amadeusFlightsList));

            return flightMapper.flightOfferSearchListToFlightAvailableDtoList(amadeusFlightsList);
        } else {
            return flightMapper.flightEntityListToFlightAvailableDtoList(existingFlights);
        }
    }

    private List<FlightEntity> existingFlightsInDB(FlightSearchRequestBody requestBody, AirportEntity departureAirport,
                                                   AirportEntity arrivalAirport) throws ParseException {

        String currency = requestBody.getCurrency();
        Integer nubmerOfPassengers = requestBody.getNumberOfPassengers();
        Integer flightsFetched = requestBody.getMax();

        Date departureDate = new SimpleDateFormat(DATE_FORMAT).parse(requestBody.getDepartureDate());
        Date returnDate = new SimpleDateFormat(DATE_FORMAT).parse(requestBody.getReturnDate());

        List<FlightEntity> flightList = flightRepository.findFlightsByAllParametres(
                departureAirport, arrivalAirport, departureDate, returnDate, nubmerOfPassengers, currency, flightsFetched);

        List<FlightEntity> flightListAllWithoutFlightsFetchedParam = flightRepository.findFlightsBySixParametres(
                departureAirport, arrivalAirport, departureDate, returnDate, nubmerOfPassengers, currency);

        if (flightList != null && flightListAllWithoutFlightsFetchedParam != null) {

            if (flightList.size() != 0 && flightList.size() == flightListAllWithoutFlightsFetchedParam.size()) {
                return flightList;
            }
        }
        return null;
    }

    private AirportEntity airportIATAcheck(String airportLocation) throws Exception {

        AirportEntity airportFound = null;

        if (airportLocation.length() > 3) {

            List<AirportEntity> airports = airportRepository.findByCityNameContainingIgnoreCase(airportLocation);

            if (airports.size() == 0) {

                String infoMessage = "There is no airports for given location: " + airportLocation;
                log.info(infoMessage);
                throw new Exception(infoMessage);
            }
            if (airports.size() == 1) {

                return airports.get(0);
            } else {

                StringBuilder sb = new StringBuilder();
                sb.append("For given location, " + airportLocation + " there is many airport IATA codes. "
                        + "Please pick one from the following values: ");

                airports.stream().forEach(
                        a -> sb.append(a.getCode() + " "));

                log.info(sb.toString());
                throw new Exception(sb.toString());
            }
        } else {
            //There is possibility to fetch two airports by same code, same alt and lon, only different id_airport in db
            //so by default, in that case, first will be taken in account
            List<AirportEntity> airportsFoundByCode = airportRepository.findByCode(airportLocation);

            if (airportsFoundByCode == null || airportsFoundByCode.isEmpty()) {
                String infoMessage = "There is no airports for given location: " + airportLocation;
                log.info(infoMessage);
                throw new Exception(infoMessage);
            }
            return airportsFoundByCode.get(0);
        }
    }*/
}
