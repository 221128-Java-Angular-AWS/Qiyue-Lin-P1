package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();
        while(reader.ready()){
            jsonBuilder.append(reader.readLine());
        }

        User user = mapper.readValue(jsonBuilder.toString(), User.class);
        try {
            service.registerNewUser(user);
            resp.setStatus(201);
        } catch (UserAlreadyExistException e) {
            resp.getWriter().print("Email is already registered");
            resp.setStatus(401);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
