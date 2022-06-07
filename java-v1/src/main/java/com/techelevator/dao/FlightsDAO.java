package com.techelevator.dao;

import com.techelevator.model.Flight;

import java.util.List;

public interface FlightsDAO {

    List<Flight> getAllFlights();

    List<Flight> getAllFlightsByTicket(int id);

    Flight getAFlight(int id);
}