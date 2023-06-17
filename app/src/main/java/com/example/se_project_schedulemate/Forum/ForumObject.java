package com.example.se_project_schedulemate.Forum;

import java.sql.Timestamp;

public class ForumObject {

    private String Title, Description, lecturer_id;
    private Long Year, Month, Day, Hour, Minute;

    public Timestamp createDeadline() {
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
