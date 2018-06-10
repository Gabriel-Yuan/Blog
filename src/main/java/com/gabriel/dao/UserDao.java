package com.gabriel.dao;

import com.gabriel.model.PageBean;
import com.gabriel.model.User;

import java.util.List;

public interface UserDao {

    public List<User> userList(PageBean pageBean, User user)throws Exception;

    public int userCount(User user)throws Exception;

    public int userDelete(String delIds)throws Exception;

    public int userSave(User user)throws Exception;

    public User getUserById(int id) throws Exception;

}
