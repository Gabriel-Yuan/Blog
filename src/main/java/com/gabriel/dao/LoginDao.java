package com.gabriel.dao;

import com.gabriel.model.Admin;
import com.gabriel.model.User;

public interface LoginDao {
    public User loginUser(User user) throws Exception;
    public Admin loginAdmin(Admin admin) throws Exception;
}
