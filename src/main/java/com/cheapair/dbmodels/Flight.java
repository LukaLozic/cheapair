package com.cheapair.dbmodels;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "FLIGHT")
@Data
@NoArgsConstructor
public class Flight {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "integer")
    @Setter(value = AccessLevel.PROTECTED)
	private Integer id;
	
	@NonNull
	@Column(name = "ID_FLIGHT", nullable = false, columnDefinition = "varchar(10)")
	private String idFlight;
	
	@NonNull
	@Column(name = "DEPARTURE_AIRPORT", nullable = false, columnDefinition = "varchar(100)")
	private String departureAirport;
	
	@NonNull
	@Column(name = "ARRIVAL_AIRPORT", nullable = false, columnDefinition = "varchar(100)")
	private String arrivalAirport;
	
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
	

}
