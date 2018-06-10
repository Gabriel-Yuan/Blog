package com.gabriel.service;

import com.gabriel.model.Admin;
import com.gabriel.model.User;

public interface LoginService {

    public User loginUser(User user) throws Exception;
    public Admin loginAdmin(Admin admin) throws Exception;
}
