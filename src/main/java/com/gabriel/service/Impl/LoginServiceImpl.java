package com.gabriel.service.Impl;

import com.gabriel.dao.LoginDao;
import com.gabriel.model.Admin;
import com.gabriel.model.User;
import com.gabriel.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginDao loginDao;
    @Override
    public User loginUser(User user) throws Exception {
        return loginDao.loginUser(user);
    }

    @Override
    public Admin loginAdmin(Admin admin) throws Exception {
        return loginDao.loginAdmin(admin);
    }
}
