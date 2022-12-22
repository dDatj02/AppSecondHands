package com.example.app2hands.Model;

import android.net.Uri;

public class User {
    Uri avatar;
    String username, phone, birth;

    public User() {
    }

    public User(Uri avatar, String username, String phone, String birth) {
        this.avatar = avatar;
        this.username = username;
        this.phone = phone;
        this.birth = birth;
    }

    public Uri getAvatar() {
        return avatar;
    }

    public void setAvatar(Uri avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
