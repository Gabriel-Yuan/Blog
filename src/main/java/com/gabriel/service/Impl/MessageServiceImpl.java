package com.gabriel.service.Impl;

import com.gabriel.dao.MessageDao;
import com.gabriel.model.Message;
import com.gabriel.model.PageBean;
import com.gabriel.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;
    @Override
    public List<Message> messageList(PageBean pageBean, Message message) throws Exception {
        return messageDao.messageList(pageBean,message);
    }

    @Override
    public int messageCount(Message message) throws Exception {
        return messageDao.messageCount(message);
    }

    @Override
    public int messageDelete(String delIds) throws Exception {
        return messageDao.messageDelete(delIds);
    }

    @Override
    public int messageSave(Message message) throws Exception {
        return messageDao.messageSave(message);
    }

}
