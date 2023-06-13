package com.example.se_project_schedulemate.Alarm;

import java.sql.Timestamp;

public class Alarm {

    private String AlarmTitle;

    private Timestamp AlarmActivation;

    private String AlarmDescription;
    private Timestamp scheduleStartTime;
    private Timestamp scheduleEndTime;

    public Alarm(String alarmTitle, Timestamp alarmActivation, String alarmDescription, Timestamp scheduleStartTime, Timestamp scheduleEndTime) {
        AlarmTitle = alarmTitle;
        AlarmActivation = alarmActivation;
        AlarmDescription = alarmDescription;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getAlarmTitle() {
        return AlarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        AlarmTitle = alarmTitle;
    }

    public Timestamp getAlarmActivation() {
        return AlarmActivation;
    }

    public void setAlarmActivation(Timestamp alarmActivation) {
        AlarmActivation = alarmActivation;
    }

    public String getAlarmDescription() {
        return AlarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        AlarmDescription = alarmDescription;
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
