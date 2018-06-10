package com.gabriel.dao.Impl;

import com.gabriel.dao.UserDao;
import com.gabriel.model.PageBean;
import com.gabriel.model.User;
import com.gabriel.util.StringUtil;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDaoImpl implements UserDao {

    private static Logger logger=LogManager.getLogger(UserDaoImpl.class.getName());
    private SessionFactory sessionFactory;
    @Override
    public List<User> userList(PageBean pageBean, User user) throws Exception {
        List<User> userList=null;
        Session session=this.getSession();
        StringBuffer stringBuffer=new StringBuffer("from User u");
        if(user!=null&&StringUtil.isNotEmpty(user.getUserName())){
            stringBuffer.append(" and u.userName like '%"+user.getUserName()+"%'");
        }
        Query query=session.createQuery(stringBuffer.toString().replaceFirst("and","where"));
        if(pageBean!=null){
            query.setFirstResult(pageBean.getStart());
            query.setMaxResults(pageBean.getRows());
        }
        userList=(List<User>)query.list();
        logger.info("查询到"+userList.size()+"位用户！");
        return userList;
    }

    @Override
    public int userCount(User user) throws Exception {
        StringBuffer stringBuffer=new StringBuffer("select count(*) as total from t_user ");
        if(StringUtil.isNotEmpty(user.getUserName())){
            stringBuffer.append("and userName like '%"+user.getUserName()+"%'");
        }
        Session session=this.getSession();
        Query query=session.createSQLQuery(stringBuffer.toString().replaceFirst("and","where"));
        return ((BigInteger)query.uniqueResult()).intValue();
    }

    @Override
    public int userDelete(String delIds) throws Exception {
        Session session=this.getSession();
        Query query=session.createQuery("delete from User where id in ("+delIds+")");
        int count=query.executeUpdate();
        logger.info("删除用户,Id为："+delIds);
        return count;
    }

    @Override
    public int userSave(User user) throws Exception {
        Session session=this.getSession();
        session.merge(user);
        logger.info("注册用户："+user.getUserName());
        return 1;
    }

    @Override
    public User getUserById(int id) throws Exception {
        Session session=this.getSession();
        User user=(User) session.get(User.class,id);
        return user;
    }


    @Resource
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
