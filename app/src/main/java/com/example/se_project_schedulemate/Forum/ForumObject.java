package com.example.se_project_schedulemate.Forum;

import android.util.Log;

import java.sql.Timestamp;

public class ForumObject {

    private String Title, Description, lecturer_id;
    private Long Year, Month, Day, Hour, Minute;

    public Timestamp createDeadline() {
        Timestamp tempTimestamp = Timestamp.valueOf(Year+"-"+Month+"-"+Day+" "+Hour+":"+Minute+":00");
//        return new Timestamp(getYear(), getMonth(), getDay(), getHour(), getMinute(), 0 ,0);
        return tempTimestamp;
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

    public String getYear() {
        return Year.toString();
    }

    public String getMonth() {
        return Month.toString();
    }

    public String getDay() {
        return Day.toString();
    }

    public String getHour() {
        return String.format("%02d", Hour.toString());
    }

    public String getMinute() {
        return String.format("%02d" ,Minute.toString());
    }
}
