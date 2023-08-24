package com.llit.entity.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "FLIGHTS")
@Data
public class FlightEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FLIGHT", nullable = false, columnDefinition = "integer")
    @Setter(value = AccessLevel.PROTECTED)
	private Integer idFlight;
    @ManyToOne
    @JoinColumn(name="departure_airport_id", nullable=false)
	private AirportEntity departureAirport;
    @ManyToOne
    @JoinColumn(name="arrival_airport_id", nullable=false)
	private AirportEntity arrivalAirport;
	@Column(name = "DEPARTURE_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	@Column(name = "RETURN_DATE")
	@Temporal(TemporalType.DATE)
	private Date returnDate;
	@Column(name = "PASSENGER_NUMBER", columnDefinition = "integer")
	private Integer passengerNumber;
	@Column(name = "CURRENCY", columnDefinition = "varchar(3)")
	private String currency;
	@Column(name = "NUMBER_OF_STOPS", columnDefinition = "integer")
	private Integer numberOfStops;
	@Column(name = "PRICE", columnDefinition = "decimal(15,2)")
	private BigDecimal price;
	@Column(name = "FLIGHTS_FETCHED", columnDefinition = "integer")
	private Integer flightsFetched;
}
