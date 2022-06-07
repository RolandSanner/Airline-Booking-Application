package com.techelevator.controller;

import com.techelevator.dao.FlightsDAO;
import com.techelevator.dao.TicketDAO;
import com.techelevator.model.Flight;
import com.techelevator.model.Ticket;
import com.techelevator.model.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AppController {

    @Autowired
    FlightsDAO flightsDAO;

    @Autowired
    TicketDAO ticketDAO;

    @RequestMapping(path="/flights", method = RequestMethod.GET)
    public List<Flight> listFlights() {
        return flightsDAO.getAllFlights();
    }

    @RequestMapping(path="/flight/{id}", method = RequestMethod.GET)
    public Flight getFlight(@PathVariable int id) {
        return flightsDAO.getAFlight(id);
    }

    /*
     */
    @RequestMapping(path="/reservation", method=RequestMethod.POST)
    public Ticket bookTicket(@RequestBody TicketDTO ticketDTO) {

        Ticket ticket = new Ticket();
        ticket.setName(ticketDTO.getName());

        for(Integer flightNum : ticketDTO.getFlights()) {
            Flight retrievedFlight = flightsDAO.getAFlight(flightNum);
            ticket.addFlight(retrievedFlight);
        }

        return ticketDAO.addTicket(ticket);

    }
}