package com.gabriel.action;


import com.gabriel.model.Admin;
import com.gabriel.service.AdminService;
import com.gabriel.util.ResponseUtil;
import com.gabriel.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Action(value = "adminAction")
@Scope("prototype")
@Namespace("/")
public class AdminAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;
    @Resource
    private AdminService adminService;
    private String error;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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


    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    @Action("Admin_login")
    public String login() {
        JSONObject result=new JSONObject();
        try {
            Admin admin=new Admin();
            admin.setUserName(userName);
            admin.setPassword(password);
            Admin currentUser = adminService.login(admin);
            if (currentUser != null) {
                result.put("success", "true");
                result.put("currentUser", currentUser.getUserName());
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", currentUser);
                ResponseUtil.write(ServletActionContext.getResponse(), result);
                return null;
            } else {
                result.put("success", "false");
                result.put("errorMsg", "用户名或者密码错误！");
                ResponseUtil.write(ServletActionContext.getResponse(), result);
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}


