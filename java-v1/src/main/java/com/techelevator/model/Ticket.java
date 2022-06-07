package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private String name;
    private List<Flight> flights = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public String toString() {
        String output = "*********************************\n";
        output += this.name + "\n";
        for (Flight flight: flights) {
            output += flight.getDepartureAirport() + flight.getMoney();
        }

        return output;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
