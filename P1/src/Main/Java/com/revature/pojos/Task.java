package com.revature.pojos;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Task {
    private Integer taskId;
    private String title;
    private String description;
    private Boolean completed;
    private Integer userId;

    public Task(){
    }

    public Task(Integer taskId, String title, String description, Boolean completed, Integer userId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    public Task(String title, String description, Boolean completed, Integer userId) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    public Task(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
