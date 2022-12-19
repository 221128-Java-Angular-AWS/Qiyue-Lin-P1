package com.revature.pojos;

public class Ticket {
    private Integer ticketId;
    private Integer amount;
    private String description;
    private String status;
    private  Integer userId;

    public Ticket() {
    }

    public Ticket(Integer ticketId, Integer amount, String description, String status, Integer userId) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }
    public Ticket(Integer amount, String description, String status) {
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Ticket(Integer amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
