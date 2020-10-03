package com.david0926.nanumfest2020.model;

public class UserModel {

    private static final String PROFILE_PLACEHOLDER = "https://raw.githubusercontent.com/roian6/FileDrive/master/%EB%A7%81%ED%81%AC%EC%9A%A9%20%EC%9D%B4%EB%AF%B8%EC%A7%80/undraw_content_team_3epn.png";

    public UserModel() {
    }

    private String name, email, time, profile, introduce;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
        time = "";
        profile = PROFILE_PLACEHOLDER;
        introduce = "";
    }

    public UserModel(String name, String email, String time, String profile, String introduce) {
        this.name = name;
        this.email = email;
        this.time = time;
        this.profile = profile;
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
