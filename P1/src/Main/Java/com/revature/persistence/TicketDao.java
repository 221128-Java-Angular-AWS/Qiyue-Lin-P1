package com.revature.persistence;

import com.revature.pojos.Ticket;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TicketDao {
    private Connection connection;
    public TicketDao(){
        this.connection = ConnectionManager.getConnection();
    }

    public void create(Ticket ticket){
        String sql = "INSERT INTO tickets (amount, description, user_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,ticket.getAmount());
            pstmt.setString(2,ticket.getDescription());
            pstmt.setInt(3,ticket.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(Integer ticketId, String status){
        String sql = "UPDATE tickets SET status = ? WHERE ticket_id = ?";
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, ticketId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Ticket> getPendingTicket() {
        Set<Ticket> results = new HashSet<>();
        try {
            String sql = "SELECT * FROM tickets WHERE status = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "pending");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                results.add( new Ticket(rs.getInt("ticket_id"), rs.getInt("amount"),
                        rs.getString("description"),rs.getString("status"),rs.getInt("user_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Set<Ticket> getTicket(Integer userId) {
        Set<Ticket> results = new HashSet<>();
        try {
            String sql = "SELECT * FROM tickets WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                results.add( new Ticket(rs.getInt("amount"),
                        rs.getString("description"),rs.getString("status")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Ticket getTicketByTicketId(Integer ticketId) {
        Ticket result = null;
        try {
            String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ticketId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            result = new Ticket(rs.getInt("amount"),
                    rs.getString("description"),
                    rs.getString("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
