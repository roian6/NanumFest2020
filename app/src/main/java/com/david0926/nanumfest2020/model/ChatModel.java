package com.david0926.nanumfest2020.model;

public class ChatModel {

    public ChatModel() {
    }

    private String name, email, profile, message;

    public ChatModel(String name, String email, String profile, String message) {
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.message = message;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
