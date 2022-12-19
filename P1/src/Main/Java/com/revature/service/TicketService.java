package com.revature.service;

import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;

import java.util.Set;

public class TicketService {
    private TicketDao dao;
    public TicketService (TicketDao dao){
        this.dao = dao;
    }

    public void postTicket(Ticket ticket){
        dao.create(ticket);
    }

    public void porcessTicket(Integer ticketId, String status){
        dao.changeStatus(ticketId, status);
    }

    public Set<Ticket> getPendingTicket(){
        return dao.getPendingTicket();
    }

    public Set<Ticket> getTicket(Integer userId){
        return dao.getTicket(userId);
    }

    public Ticket getTicketByTicketId (Integer ticketId){
        return dao.getTicketByTicketId(ticketId);
    }
}
