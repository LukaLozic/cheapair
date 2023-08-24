package com.llit.entity.repository;
import com.llit.entity.dao.AirportEntity;
import com.llit.entity.dao.FlightEntity;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FlightRepository extends CrudRepository<FlightEntity, Integer>{
	@Query(value = "SELECT * FROM Flights a WHERE "
			+ "a.departure_airport_id = :departureAirport AND "
			+ "a.arrival_airport_id = :arrivalAirport AND "
			+ "a.departure_date = :departureDate AND "
			+ "a.return_date = :arrivalDate AND "
			+ "a.passenger_number = :passengerNumber AND "
			+ "a.currency = :currency AND "
			+ "a.flights_fetched = :flightsFetched", 			
			  nativeQuery = true)
	List<FlightEntity> findFlightsByAllParametres(
			@Param("departureAirport") AirportEntity departureAirport,
			@Param("arrivalAirport") AirportEntity arrivalAirport,
			@Param("departureDate") Date departureDate,
			@Param("arrivalDate") Date arrivalDate,
			@Param("passengerNumber") Integer passengerNumber,
			@Param("currency") String currency,
			@Param("flightsFetched") Integer flightsFetched
			);
	
	@Query(value = "SELECT * FROM Flights a WHERE "
			+ "a.departure_airport_id = :departureAirport AND "
			+ "a.arrival_airport_id = :arrivalAirport AND "
			+ "a.departure_date = :departureDate AND "
			+ "a.return_date = :arrivalDate AND "
			+ "a.passenger_number = :passengerNumber AND "
			+ "a.currency = :currency",
			  nativeQuery = true)
	List<FlightEntity> findFlightsBySixParametres(
			@Param("departureAirport") AirportEntity departureAirport,
			@Param("arrivalAirport") AirportEntity arrivalAirport,
			@Param("departureDate") Date departureDate,
			@Param("arrivalDate") Date arrivalDate,
			@Param("passengerNumber") Integer passengerNumber,
			@Param("currency") String currency
			);
}
