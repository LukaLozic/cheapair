package com.llit.entity.repository;
import com.llit.entity.dao.AirportEntity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface AirportRepository extends CrudRepository<AirportEntity, Integer>{
	List<AirportEntity> findByName(String name);
	List<AirportEntity> findByCityNameContainingIgnoreCase(String name);
	List<AirportEntity> findByCode(String name);
}
