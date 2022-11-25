package com.cheapair.repositories;
import com.cheapair.dbmodels.Airport;
import com.cheapair.dbmodels.Flight;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FlightRepository extends CrudRepository<Flight, Integer>{

	
	@Query(value = "SELECT * FROM Flights a WHERE "
			+ "a.departure_airport_id = :departureAirport AND "
			+ "a.arrival_airport_id = :arrivalAirport AND "
			+ "a.departure_date = :departureDate AND "
			+ "a.return_date = :arrivalDate AND "
			+ "a.passenger_number = :passengerNumber AND "
			+ "a.currency = :currency", 			
			  nativeQuery = true)
	List<Flight> findAirports(
			@Param("departureAirport") Airport departureAirport, 
			@Param("arrivalAirport") Airport arrivalAirport,
			@Param("departureDate") Date departureDate,
			@Param("arrivalDate") Date arrivalDate,
			@Param("passengerNumber") Integer passengerNumber,
			@Param("currency") String currency
			);
	
}
