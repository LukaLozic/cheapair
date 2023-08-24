package com.llit.dto;

import lombok.Data;

import java.util.List;

@Data
public class FlightResponseBody {
	List<FlightAvailableDto> flightsAvailable;
}
