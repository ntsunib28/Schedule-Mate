package com.example.se_project_schedulemate.Forum;

import java.sql.Timestamp;

public class Forum {

    private String forumTitle, forumSession, forumLecturer;
    private Timestamp forumDeadline;

    public Forum(String forumTitle, String forumSession, String forumLecturer, Timestamp forumDeadline) {
        this.forumTitle = forumTitle;
        this.forumSession = forumSession;
        this.forumLecturer = forumLecturer;
        this.forumDeadline = forumDeadline;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumSession() {
        return forumSession;
    }

    public void setForumSession(String forumSession) {
        this.forumSession = forumSession;
    }

    public String getForumLecturer() {
        return forumLecturer;
    }

    public void setForumLecturer(String forumLecturer) {
        this.forumLecturer = forumLecturer;
    }

    public Timestamp getForumDeadline() {
        return forumDeadline;
    }

    public void setForumDeadline(Timestamp forumDeadline) {
        this.forumDeadline = forumDeadline;
    }
}
