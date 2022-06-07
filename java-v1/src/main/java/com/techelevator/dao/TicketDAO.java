package com.techelevator.dao;

import com.techelevator.model.Ticket;

public interface TicketDAO {

    Ticket addTicket(Ticket ticket);

    Ticket getTicket(int ticketId);
}