package com.llit.service;

import com.llit.dto.FlightAvailableDto;
import com.llit.dto.FlightSearchRequestBody;

import java.util.List;

public interface FlightService {
    List<FlightAvailableDto> retrieveAmadeusFlights(FlightSearchRequestBody requestBody) throws Exception;
}
