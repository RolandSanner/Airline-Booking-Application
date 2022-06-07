package com.techelevator.dao;

import com.techelevator.model.Flight;
import com.techelevator.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcTicketDAO implements TicketDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FlightsDAO flightsDAO;

    public JdbcTicketDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (customer_name) VALUES(?) RETURNING ticket_id";
        int newTicketId =  jdbcTemplate.queryForObject(sql, Integer.class, ticket.getName());

        for (Flight flight: ticket.getFlights()) {

            String sql2 = "INSERT INTO ticket_flight(ticket_id, flight_id) VALUES(?,?)";
            jdbcTemplate.update(sql2, newTicketId, flight.getFlightId());

        }

        return getTicket(newTicketId);
    }

    @Override
    public Ticket getTicket(int ticketId) {

        Ticket ticket = new Ticket();
        String sql = "SELECT customer_name from ticket WHERE ticket_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ticketId);

        if(results.next()) {
            ticket.setName(results.getString("customer_name"));
        }

        List<Flight> flightsForThisTicket = flightsDAO.getAllFlightsByTicket(ticketId);

        for (Flight flight : flightsForThisTicket) {
            ticket.addFlight(flight);
        }

        return ticket;
    }
}
