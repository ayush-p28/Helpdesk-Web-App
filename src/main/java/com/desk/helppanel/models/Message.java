package com.desk.helppanel.models;

public class Message {

    private String id;
    private String time;
    private String sender;
    private String recipient;
    private String timeStamp;
    private String messageId;
    private String message;

    public Message(){}

    public Message(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public Message(String id, String time, String sender, String recipient, String timeStamp, String messageId,
            String message) {
        this.id = id;
        this.time = time;
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
        this.messageId = messageId;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return recipient;
    }
    public void setReceiver(String receiver) {
        this.recipient = receiver;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
