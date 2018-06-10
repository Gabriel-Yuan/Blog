package com.gabriel.action;

import com.gabriel.model.Admin;
import com.gabriel.model.Article;
import com.gabriel.model.PageBean;
import com.gabriel.model.User;
import com.gabriel.service.ArticleService;
import com.gabriel.service.LoginService;
import com.gabriel.util.DateUtil;
import com.gabriel.util.ResponseUtil;
import com.gabriel.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Action(value = "LoginAction")
@Scope("prototype")
@Namespace("/")
public class LoginAction extends ActionSupport implements ServletRequestAware {
    @Resource
    private LoginService loginService;
    private HttpServletRequest request;
    private String userName;
    private String password;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

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



    @Action(value = "LoginFindAction")
    public String list() throws Exception {
        JSONObject result = new JSONObject();
        Admin admin=new Admin();
        User user=new User();
        admin.setUserName(userName);
        admin.setPassword(password);
        user.setUserName(userName);
        user.setPassword(password);
        try {
            Admin currentUser1 = loginService.loginAdmin(admin);
            User currentUser2 = loginService.loginUser(user);
            if (currentUser1 != null) {
                result.put("success", "true");
                result.put("identity", "true");
                result.put("currentUser", currentUser1.getUserName());
                HttpSession session = request.getSession();
                session.setAttribute("identity", "true");
                session.setAttribute("id", currentUser1.getId());
                session.setAttribute("currentUser", currentUser1.getUserName());
                ResponseUtil.write(ServletActionContext.getResponse(), result);
                return null;
            } else if (currentUser2 != null) {
                result.put("success", "true");
                result.put("identity", "false");
                result.put("currentUser", currentUser2.getUserName());
                HttpSession session = request.getSession();
                session.setAttribute("identity", "false");
                session.setAttribute("id", currentUser2.getId());
                session.setAttribute("currentUser", currentUser2.getUserName());
                ResponseUtil.write(ServletActionContext.getResponse(), result);
                return null;

            } else {
                result.put("success", "false");
                result.put("errorMsg", "用户名或者密码错误！");
                ResponseUtil.write(ServletActionContext.getResponse(), result);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
