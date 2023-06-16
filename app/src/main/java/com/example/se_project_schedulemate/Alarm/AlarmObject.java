package com.example.se_project_schedulemate.Alarm;

import java.sql.Timestamp;

public class AlarmObject {

    // ini tes biar bisa masukin dari firebase!!!!!!

    private Long Year, Month, Day, Hour, Minute;
    private Long EndHour, EndMinute;
    private String Title;
    private String Description;

//    public AlarmObject(int Year, int Month, int Day, int Hour, int Minute, int EndHour, int EndMinute, String Title) {
//        this.Year = Year;
//        this.Month = Month;
//        this.Day = Day;
//        this.Hour = Hour;
//        this.Minute = Minute;
//        this.EndHour = EndHour;
//        this.EndMinute = EndMinute;
//        this.Title = Title;
//    }

    public Timestamp createAlarmActivation() {
        return new Timestamp(Math.toIntExact(getYear()), Math.toIntExact(getMonth()), Math.toIntExact(getDay()), Math.toIntExact(getHour()), Math.toIntExact(getMinute()), 0 ,0);
    }

    public Timestamp createScheduleStartTime(){
        return new Timestamp(0, 0, 0, Math.toIntExact(getHour()), Math.toIntExact(getMinute()), 0, 0);
    }

    public Timestamp createScheduleEndTime() {
        return new Timestamp(0, 0, 0, Math.toIntExact(getEndHour()), Math.toIntExact(getEndMinute()), 0, 0);
    }

    public Long getYear() {
        return Year;
    }

    public void setYear(Long year) {
        Year = year;
    }

    public Long getMonth() {
        return Month;
    }

    public void setMonth(Long month) {
        Month = month;
    }

    public Long getDay() {
        return Day;
    }

    public void setDay(Long day) {
        Day = day;
    }

    public Long getHour() {
        return Hour;
    }

    public void setHour(Long hour) {
        Hour = hour;
    }

    public Long getMinute() {
        return Minute;
    }

    public void setMinute(Long minute) {
        Minute = minute;
    }

    public Long getEndHour() {
        return EndHour;
    }

    public void setEndHour(Long endHour) {
        EndHour = endHour;
    }

    public Long getEndMinute() {
        return EndMinute;
    }

    public void setEndMinute(Long endMinute) {
        EndMinute = endMinute;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
