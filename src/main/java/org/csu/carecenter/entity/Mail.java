package org.csu.carecenter.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Mail {

    private String from;
    private String subject;
    private Date sentDate;
    private StringBuffer content;

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
