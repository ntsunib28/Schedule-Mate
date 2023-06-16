package com.example.se_project_schedulemate.Alarm;

import java.sql.Timestamp;

public class Alarm {

    private String alarmTitle;

    private Timestamp alarmActivation;

    private String alarmDescription;
    private Timestamp scheduleStartTime;
    private Timestamp scheduleEndTime;

    public Alarm(String alarmTitle, Timestamp alarmActivation, String alarmDescription, Timestamp scheduleStartTime, Timestamp scheduleEndTime) {
        this.alarmTitle = alarmTitle;
        this.alarmActivation = alarmActivation;
        this.alarmDescription = alarmDescription;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        alarmTitle = alarmTitle;
    }

    public Timestamp getAlarmActivation() {
        return alarmActivation;
    }

    public void setAlarmActivation(Timestamp alarmActivation) {
        alarmActivation = alarmActivation;
    }

    public String getAlarmDescription() {
        return alarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        alarmDescription = alarmDescription;
    }

    public Timestamp getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Timestamp scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Timestamp getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(Timestamp scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }
}
