package com.llit.entity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "AIRPORTS")
@Data
public class AirportEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AIRPORT", nullable = false, columnDefinition = "integer")
    @Setter(value = AccessLevel.PROTECTED)
	private Integer idAirport;
	@Column(name = "CODE", columnDefinition = "varchar(50)")
	private String code;
	@Column(name = "NAME", columnDefinition = "varchar(200)")
	private String name;
	@Column(name = "CITY_CODE", columnDefinition = "varchar(50)")
	private String cityCode;
	@Column(name = "CITY_NAME", columnDefinition = "varchar(200)")
	private String cityName;
	@Column(name = "COUNTRY_NAME", columnDefinition = "varchar(200)")
	private String countryName;
	@Column(name = "COUNTRY_CODE", columnDefinition = "varchar(200)")
	private String countryCode;
	@Column(name = "TIME_ZONE", columnDefinition = "varchar(8)")
	private String timeZone;
	@Column(name = "LAT", columnDefinition = "varchar(32)")
	private String lat;
	@Column(name = "LON", columnDefinition = "varchar(32)")
	private String lon;
	@Column(name = "NUM_AIRPORTS")
	private String numAirports;
	@Column(name = "CITY", columnDefinition = "varchar(5)")
	private String city;
	@OneToMany(mappedBy="departureAirport")
    private List<FlightEntity> departureFlights = new ArrayList<>();
	@OneToMany(mappedBy="arrivalAirport")
    private List<FlightEntity> arrivalFlights = new ArrayList<>();
}
