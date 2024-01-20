package com.example.pozischatapp;

import java.util.Date;

public class Message {
    public String name,textMessage;
    private long messageTime;
    public Message(){

    }
    public Message(String name,String textMessage){
        this.name = name;
        this.textMessage = textMessage;

        this.messageTime = new Date().getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
