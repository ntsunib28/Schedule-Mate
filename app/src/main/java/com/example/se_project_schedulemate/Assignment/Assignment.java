package com.example.se_project_schedulemate.Assignment;

import java.sql.Timestamp;

public class Assignment {
    private String assignmentName;
    private Timestamp notification;
    private Integer session;
    private String lecturer;
    private Timestamp deadline;

    public Assignment(String assignmentName, Timestamp notification, Integer session, String lecturer, Timestamp deadline) {
        this.assignmentName = assignmentName;
        this.notification = notification;
        this.session = session;
        this.lecturer = lecturer;
        this.deadline = deadline;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Timestamp getNotification() {
        return notification;
    }

    public void setNotification(Timestamp notification) {
        this.notification = notification;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
}
