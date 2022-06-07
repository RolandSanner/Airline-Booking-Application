package com.techelevator.model;

import java.util.List;

public class TicketDTO {

    private String name;
    private List<Integer> flights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "name='" + name + '\'' +
                ", flights=" + flights +
                '}';
    }

    public List<Integer> getFlights() {
        return flights;
    }

    public void setFlights(List<Integer> flights) {
        this.flights = flights;
    }
}