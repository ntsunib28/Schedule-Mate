package com.example.se_project_schedulemate.Alarm;

import java.sql.Timestamp;

public class AlarmObject {

    // ini tes biar bisa masukin dari firebase!!!!!!

    private Long Year, Month, Day, Hour, Minute;
    private Long EndHour, EndMinute;
    private String Title;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

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
        return new Timestamp(getYear(), getMonth(), getDay(), getHour(), getMinute(), 0 ,0);
    }

    public Timestamp createScheduleStartTime(){
        return new Timestamp(0, 0, 0, getHour(), getMinute(), 0, 0);
    }

    public Timestamp createScheduleEndTime() {
        return new Timestamp(0, 0, 0, getEndHour(), getEndMinute(), 0, 0);
    }

    public int getYear() {
        return Math.toIntExact(Year);
    }

    public void setYear(int year) {
        Year = Long.valueOf(year);
    }

    public int getMonth() {
        return Math.toIntExact(Month);
    }

    public void setMonth(int month) {
        Month = Long.valueOf(month);
    }

    public int getDay() {
        return Math.toIntExact(Day);
    }

    public void setDay(int day) {
        Day = Long.valueOf(day);
    }

    public int getHour() {
        return Math.toIntExact(Hour);
    }

    public void setHour(int hour) {
        Hour = Long.valueOf(hour);
    }

    public int getMinute() {
        return Math.toIntExact(Minute);
    }

    public void setMinute(int minute) {
        Minute = Long.valueOf(minute);
    }

    public int getEndHour() {
        return Math.toIntExact(EndHour);
    }

    public void setEndHour(int endHour) {
        EndHour = Long.valueOf(endHour);
    }

    public int getEndMinute() {
        return Math.toIntExact(EndMinute);
    }

    public void setEndMinute(int endMinute) {
        EndMinute = Long.valueOf(endMinute);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

}
