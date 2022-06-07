package com.techelevator.dao;

import com.techelevator.model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFlightsDAO implements FlightsDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcFlightsDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    private Flight flightObjectMapper(SqlRowSet results) {

        Flight flight = new Flight();
        flight.setFlightId(results.getLong("flight_id"));
        flight.setFlightNo(results.getString("flight_no"));
        flight.setScheduledDeparture(results.getTimestamp("scheduled_departure").toLocalDateTime());
        flight.setScheduledArrival(results.getTimestamp("scheduled_arrival").toLocalDateTime());
        flight.setArrivalAirport(results.getString("arrival_airport"));
        flight.setDepartureAirport(results.getString("departure_airport"));
        flight.setAircraftCode(results.getString("aircraft_code"));
        flight.setMoney(results.getBigDecimal("price"));

        return flight;
    }

    @Override
    public List<Flight> getAllFlights() {
        String sql = "SELECT * from flights;";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);
        List<Flight> flights = new ArrayList<>();
        while (results.next()) {
            flights.add(flightObjectMapper(results));

        }
        return flights;
    }

    @Override
    public List<Flight> getAllFlightsByTicket(int ticketId) {
        String sql = "SELECT * from flights JOIN ticket_flight ON flights.flight_id = ticket_flight.flight_id where ticket_id = ?";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, ticketId);
        List<Flight> flights = new ArrayList<>();
        while (results.next()) {
            flights.add(flightObjectMapper(results));

        }
        return flights;
    }

    @Override
    public Flight getAFlight(int id) {

        String sql = "SELECT * FROM flights where flight_id = ?";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);

        Flight flight = null;
        if(results.next()) {
            flight = flightObjectMapper(results);
        }

        return flight;
    }
}