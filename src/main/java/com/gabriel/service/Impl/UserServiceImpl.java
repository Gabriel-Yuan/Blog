package com.gabriel.service.Impl;

import com.gabriel.dao.UserDao;
import com.gabriel.model.PageBean;
import com.gabriel.model.User;
import com.gabriel.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public List<User> userList(PageBean pageBean, User user) throws Exception {
        return userDao.userList(pageBean,user);
    }

    @Override
    public int userCount(User user) throws Exception {
        return userDao.userCount(user);
    }

    @Override
    public int userDelete(String delIds) throws Exception {
        return userDao.userDelete(delIds);
    }

    @Override
    public int userSave(User user) throws Exception {
        return userDao.userSave(user);
    }

    @Override
    public User getUserById(int id) throws Exception {
        return userDao.getUserById(id);
    }


}
