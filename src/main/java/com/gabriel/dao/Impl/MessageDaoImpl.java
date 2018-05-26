package com.gabriel.dao.Impl;

import com.gabriel.dao.MessageDao;
import com.gabriel.model.Message;
import com.gabriel.model.PageBean;
import com.gabriel.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {


    private SessionFactory sessionFactory;

    @Override
    public List<Message> messageList(PageBean pageBean, Message message) throws Exception {
        List<Message> messageList = null;
        Session session = this.getSession();
        StringBuffer stringBuffer = new StringBuffer("from Message m");
        if (message !=null&&StringUtil.isNotEmpty(message.getMessageContent())) {
            stringBuffer.append(" and m.messageContent like '%" + message.getMessageContent() + "%'");
        }
        Query query = session.createQuery(stringBuffer.toString().replaceFirst("and", "where"));
        if (pageBean != null && message.getMessageId() > -1) {
            query.setFirstResult(pageBean.getStart());
            query.setMaxResults(pageBean.getRows());
        }
        messageList = query.list();
        return messageList;
    }

    @Override
    public int messageCount(Message message) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("select count(*) as total from t_message");
        if (message !=null&&StringUtil.isNotEmpty(message.getMessageContent())) {
            stringBuffer.append(" and message_content like '%" + message.getMessageContent() + "%'");
        }
        Session session = this.getSession();
        Query query = session.createSQLQuery(stringBuffer.toString().replaceFirst("and", "where"));
        return ((BigInteger) query.uniqueResult()).intValue();
    }

    @Override
    public int messageDelete(String delIds) throws Exception {
        Session session = this.getSession();
        Query query = session.createSQLQuery("delete from t_message where message_id in(" + delIds + ")");
        int count = query.executeUpdate();
        return count;
    }


    @Override
    public int messageSave(Message message) throws Exception {
        Session session = this.getSession();
        session.merge(message);
        return 1;
    }



    @Resource
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
