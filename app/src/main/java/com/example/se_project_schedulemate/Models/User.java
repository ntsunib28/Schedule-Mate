package com.example.se_project_schedulemate.Models;

public class User {
    //User Data
    private String class_group;

    private String email;
    private String gender;
    private String name;
    private String nim;
    private String photo;
    private String user_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getClass_group() {
        return class_group;
    }

    public void setClass_group(String class_group) {
        this.class_group = class_group;
    }

//    public User(String email, String gender, String name, String nim, String photo, String user_id) {
//        this.email = email;
//        this.gender = gender;
//        this.name = name;
//        this.nim = nim;
//        this.photo = photo;
//        this.user_id = user_id;
//    }
}
