package com.cheapair.repositories;
import com.cheapair.dbmodels.Airport;
import com.cheapair.dbmodels.Flight;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface AirportRepository extends CrudRepository<Airport, Integer>{

	List<Airport> findByName(String name);
	
	List<Airport> findByCityNameContainingIgnoreCase(String name);
	
	List<Airport> findByCode(String name);

}
