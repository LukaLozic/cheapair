package com.llit.mapper;

import com.amadeus.resources.FlightOfferSearch;
import com.llit.dto.FlightAvailableDto;
import com.llit.entity.dao.FlightEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper
{
 /*   FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class );
    @Mapping(target="price", source="flightOfferSearch.price.total")
    FlightAvailableDto flightOfferSearchToFlightAvailableDto(FlightOfferSearch flightOfferSearch);
    @Mapping(target="price", source="flightOfferSearch.price.total")
    List<FlightAvailableDto> flightOfferSearchListToFlightAvailableDtoList(List<FlightOfferSearch> flightOfferSearches);


    @Mapping(target="price", source="flightOfferSearch.price.total")
    FlightEntity flightOfferSearchToFlightEntity(FlightOfferSearch flightOfferSearch);
    @Mapping(target="price", source="flightOfferSearch.price.total")
    List<FlightEntity> flightOfferSearchListToFlightEntityList(List<FlightOfferSearch> flightOfferSearches);

    FlightAvailableDto flightEntityToFlightAvailableDto(FlightEntity flightEntity);
    List<FlightAvailableDto> flightEntityListToFlightAvailableDtoList(List<FlightEntity> flightEntities);
*/}