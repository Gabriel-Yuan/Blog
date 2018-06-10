package com.gabriel.dao.Impl;

import com.gabriel.dao.LoginDao;
import com.gabriel.model.Admin;
import com.gabriel.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class LoginDaoImpl  extends HibernateDaoSupport implements LoginDao {
    private SessionFactory sessionFactory;
    private static Logger logger=LogManager.getLogger(LoginDaoImpl.class.getName());
    @Override
    public User loginUser(User user) throws Exception {
        User resultUser=null;
        @SuppressWarnings("unchecked")
        List<User> userList= (List<User>) getHibernateTemplate().find("from User u where u.userName=? and u.password=?",user.getUserName(),user.getPassword());
        if (userList.size()>0){
            resultUser=userList.get(0);
            logger.info("普通用户："+resultUser.getUserName()+"登录成功！");
        }
        return  resultUser;
    }

    @Override
    public Admin loginAdmin(Admin admin) throws Exception {
        Admin resultUser=null;
        @SuppressWarnings("unchecked")
        List<Admin> userList= (List<Admin>) getHibernateTemplate().find("from Admin u where u.userName=? and u.password=?",admin.getUserName(),admin.getPassword());
        if (userList.size()>0){
            resultUser=userList.get(0);
            logger.info("管理员："+resultUser.getUserName()+"登录成功！");
        }
        return  resultUser;
    }

    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
