package com.gabriel.service.Impl;

import com.gabriel.dao.AdminDao;
import com.gabriel.model.Admin;
import com.gabriel.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    public Admin login(Admin user) throws Exception{
        return adminDao.login(user);
    }
}
