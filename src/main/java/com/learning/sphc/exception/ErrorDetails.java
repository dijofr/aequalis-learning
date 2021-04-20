package com.learning.sphc.exception;

import java.util.Date;

public class ErrorDetails {

    private Date timeStamp;
    private String messagel;
    private String details;

    public ErrorDetails(Date timeStamp, String messagel, String details) {
        this.timeStamp = timeStamp;
        this.messagel = messagel;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessagel() {
        return messagel;
    }

    public void setMessagel(String messagel) {
        this.messagel = messagel;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
