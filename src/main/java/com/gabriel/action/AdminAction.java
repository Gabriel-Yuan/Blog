package com.gabriel.action;


import com.gabriel.model.Admin;
import com.gabriel.service.AdminService;
import com.gabriel.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Action(value = "adminAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
@Results({
        @Result(name="login_success",location = "/WEB-INF/super_admin/main.html"),
        @Result(name="login_fail",location = "admin_login.jsp")
})
public class AdminAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;
    @Resource
    private AdminService adminService;
    private String error;
    private Admin user;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminServiceImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    public Admin getUser() {
        return user;
    }

    public void setUser(Admin user) {
        this.user = user;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    @Action("Admin_login")
    public String login() {
        HttpSession session = request.getSession();
        if (StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getPassword())) {
            error = "用户名或密码为空！";
            return "login_fail";
        }
        try {
            Admin currentUser = adminService.login(user);
            if (currentUser == null) {
                error = "用户名或密码错误！";
                return "login_fail";
            } else {

                session.setAttribute("currentUser", currentUser);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "login_success";
    }
}


