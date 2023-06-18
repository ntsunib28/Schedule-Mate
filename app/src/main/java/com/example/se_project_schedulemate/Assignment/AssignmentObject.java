package com.example.se_project_schedulemate.Assignment;

import java.sql.Timestamp;

public class AssignmentObject {
    int deadline_date, deadline_hour, deadline_minute, deadline_month, deadline_year, session;
    String title;

    public Timestamp createDeadlineDate(){

        // Masukin semua datanya ke wrapper
        Integer dl_date, dl_hour, dl_minute, dl_month, dl_year;
        dl_date = deadline_date;
        dl_hour = deadline_hour;
        dl_minute = deadline_minute;
        dl_month = deadline_month;
        dl_year = deadline_year;

        String minute_str = String.format("%02d", dl_minute);
        String hour_str = String.format("%02d", dl_hour);

        Timestamp tempTimestamp = Timestamp.valueOf(dl_year+"-"+dl_month+"-"+dl_date+" "+hour_str+":"+minute_str+":00");
        return tempTimestamp;
    }

    public int getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(int deadline_date) {
        this.deadline_date = deadline_date;
    }

    public int getDeadline_hour() {
        return deadline_hour;
    }

    public void setDeadline_hour(int deadline_hour) {
        this.deadline_hour = deadline_hour;
    }

    public int getDeadline_minute() {
        return deadline_minute;
    }

    public void setDeadline_minute(int deadline_minute) {
        this.deadline_minute = deadline_minute;
    }

    public int getDeadline_month() {
        return deadline_month;
    }

    public void setDeadline_month(int deadline_month) {
        this.deadline_month = deadline_month;
    }

    public int getDeadline_year() {
        return deadline_year;
    }

    public void setDeadline_year(int deadline_year) {
        this.deadline_year = deadline_year;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
