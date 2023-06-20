package com.example.se_project_schedulemate.Models;

public class Settings {
    int assignment_reminder_days;
    int class_alarm_minutes;
    int forum_reminder_days;
    public int getAssignment_reminder_days() {
        return assignment_reminder_days;
    }

    public void setAssignment_reminder_days(int assignment_reminder_days) {
        this.assignment_reminder_days = assignment_reminder_days;
    }

    public int getClass_alarm_minutes() {
        return class_alarm_minutes;
    }

    public void setClass_alarm_minutes(int class_alarm_minutes) {
        this.class_alarm_minutes = class_alarm_minutes;
    }

    public int getForum_reminder_days() {
        return forum_reminder_days;
    }

    public void setForum_reminder_days(int forum_reminder_days) {
        this.forum_reminder_days = forum_reminder_days;
    }


}
