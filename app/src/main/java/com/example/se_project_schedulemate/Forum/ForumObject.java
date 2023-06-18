package com.example.se_project_schedulemate.Forum;

import android.util.Log;

import java.sql.Timestamp;

public class ForumObject {

    private String Title, Description, lecturer_id;
    private Long Year, Month, Day, Hour, Minute;

    public Timestamp createDeadline() {
        Timestamp newtimestamp = Timestamp.valueOf("2023-12-10 12:59:00");
        return new Timestamp(getYear(), getMonth(), getDay(), getHour(), getMinute(), 0 ,0);
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLecturer_id() {
        return lecturer_id;
    }

    public int getYear() {
        return Math.toIntExact(Year);
    }

    public int getMonth() {
        return Math.toIntExact(Month);
    }

    public int getDay() {
        return Math.toIntExact(Day);
    }

    public int getHour() {
        return Math.toIntExact(Hour);
    }

    public int getMinute() {
        return Math.toIntExact(Minute);
    }
}
