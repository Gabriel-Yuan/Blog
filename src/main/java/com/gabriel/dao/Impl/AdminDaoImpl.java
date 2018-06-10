package com.gabriel.dao.Impl;

import com.gabriel.dao.AdminDao;
import com.gabriel.model.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
    private SessionFactory sessionFactory;
    private static Logger logger = LogManager.getLogger(AdminDaoImpl.class.getName());
    public Admin login(Admin user) throws Exception{
        Admin resultUser=null;
        @SuppressWarnings("unchecked")
        List<Admin> userList= (List<Admin>) getHibernateTemplate().find("from Admin u where u.userName=? and u.password=?",user.getUserName(),user.getPassword());
        if (userList.size()>0){
            resultUser=userList.get(0);
        }
        logger.info("管理员："+resultUser.getUserName()+"登录成功！");
        return  resultUser;
    }

    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory) {
       super.setSessionFactory(sessionFactory);
    }
}
