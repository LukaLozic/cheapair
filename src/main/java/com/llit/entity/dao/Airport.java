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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AIRPORTS")
@Data
@NoArgsConstructor
public class Airport {

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
    private List<Flight> departureFlights = new ArrayList<>();
	
	@OneToMany(mappedBy="arrivalAirport")
    private List<Flight> arrivalFlights = new ArrayList<>();

	public Integer getIdAirport() {
		return idAirport;
	}

	public void setIdAirport(Integer idAirport) {
		this.idAirport = idAirport;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getNumAirports() {
		return numAirports;
	}

	public void setNumAirports(String numAirports) {
		this.numAirports = numAirports;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}

	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}

	public List<Flight> getArrivalFlights() {
		return arrivalFlights;
	}

	public void setArrivalFlights(List<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}
	



	

}
