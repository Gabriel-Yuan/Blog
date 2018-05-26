package com.gabriel.dao;


import com.gabriel.model.Message;
import com.gabriel.model.PageBean;

import java.util.List;

public interface MessageDao {
    public List<Message> messageList(PageBean pageBean,Message message) throws Exception;
    public int messageCount(Message message) throws Exception;
    public int messageDelete(String delIds) throws Exception;
    public int messageSave(Message message) throws Exception;

}
