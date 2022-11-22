package com.cheapair.repositories;
import com.cheapair.dbmodels.Flight;
import org.springframework.data.repository.CrudRepository;


public interface FlightRepository extends CrudRepository<Flight, Integer>{

}
