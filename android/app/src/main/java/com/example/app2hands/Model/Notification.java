package com.example.app2hands.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Notification implements Parcelable {
    String title, description;

    public Notification() {
    }

    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
    }

    protected Notification(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
    }
}
