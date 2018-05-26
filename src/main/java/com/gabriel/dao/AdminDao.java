package com.gabriel.dao;

import com.gabriel.model.Admin;

public interface AdminDao {
    public Admin login(Admin user) throws Exception;
}
