package com.gabriel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_message", schema = "blog")
public class Message {
    private int messageId;
    private String messageContent;
    private Date messageDate;
    private int mUserId;


    public Message(int messageId, String messageContent, Date messageDate, int mUserId) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.messageDate = messageDate;
        this.mUserId = mUserId;

    }

    public Message() {
    }

    @Id
    @Column(name = "message_id")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "message_content")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "message_date")
    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    @Basic
    @Column(name = "m_user_id")
    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != message.messageId) return false;
        if (mUserId != message.mUserId) return false;

        if (messageContent != null ? !messageContent.equals(message.messageContent) : message.messageContent != null)
            return false;
        if (messageDate != null ? !messageDate.equals(message.messageDate) : message.messageDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + (messageDate != null ? messageDate.hashCode() : 0);
        result = 31 * result + mUserId;

        return result;
    }
}
