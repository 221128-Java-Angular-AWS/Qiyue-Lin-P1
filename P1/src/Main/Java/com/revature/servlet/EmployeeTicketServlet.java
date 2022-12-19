package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;
import com.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class EmployeeTicketServlet extends HttpServlet {
    private TicketService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = -1;
        Cookie [] cookies = req.getCookies();
        for (Cookie cookie : cookies ) {
            if (cookie.getName().equals("userId"))
                userId = Integer.parseInt(cookie.getValue());
        }
        Set<Ticket> tickets = service.getTicket(userId);
        String json = mapper.writeValueAsString(tickets);
        resp.setStatus(200);
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();
        while(reader.ready()){
            jsonBuilder.append(reader.readLine());
        }
        Ticket ticket = mapper.readValue(jsonBuilder.toString(), Ticket.class);

        Integer userId = -1;
        Cookie [] cookies = req.getCookies();
        for (Cookie cookie : cookies ) {
            if (cookie.getName().equals("userId"))
                userId = Integer.parseInt(cookie.getValue());
        }
        ticket.setUserId(userId);
        service.postTicket(ticket);
        resp.setStatus(201);
    }
}
