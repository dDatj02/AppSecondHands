package com.example.app2hands.Model;

public class Message {
    private int img;
    private String name, msg;

    public Message(int img, String name, String msg) {
        this.img = img;
        this.name = name;
        this.msg = msg;
    }

    public Message() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
