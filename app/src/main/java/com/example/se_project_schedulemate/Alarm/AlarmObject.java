package com.example.se_project_schedulemate.Alarm;

import java.sql.Timestamp;

public class AlarmObject {

    // ini tes biar bisa masukin dari firebase!!!!!!

    private Long Year, Month, Day, Hour, Minute;
    private Long EndHour, EndMinute;
    private String Title;
    private String Description;

    public Timestamp createAlarmActivation() {
        Timestamp tempTimestamp = Timestamp.valueOf(Year+"-"+Month+"-"+Day+" "+Hour+":"+Minute+":00");
        return tempTimestamp;
    }

    public Timestamp createScheduleStartTime(){
        Timestamp tempTimestamp = Timestamp.valueOf(Year+"-"+Month+"-"+Day+" "+Hour+":"+Minute+":00");
        return tempTimestamp;
    }

    public Timestamp createScheduleEndTime() {
        Timestamp tempTimestamp = Timestamp.valueOf(Year+"-"+Month+"-"+Day+" "+EndHour+":"+EndMinute+":00");
        return tempTimestamp;
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
        return String.format("%02d", Minute.toString());
    }

    public String getEndHour() {
        return String.format("%02d", EndHour.toString());
    }

    public String getEndMinute() {
        return String.format("%02d", EndMinute.toString());
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

}
