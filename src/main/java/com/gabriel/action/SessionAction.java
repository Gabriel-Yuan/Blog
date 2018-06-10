package com.gabriel.action;

import com.gabriel.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Action(value = "SessionAction")
@Scope("prototype")
@Namespace("/")
public class SessionAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;

    @Action(value = "GetSession")
    public String GetSession() throws Exception {
        JSONObject result=new JSONObject();
        HttpSession session = request.getSession();
        if(session.getAttribute("identity")==null ){
            result.put("checkLogin","false");
        } else{
            String identity = (String) session.getAttribute("identity");
            String currentUser= (String) session.getAttribute("currentUser");
            int id  = (Integer) session.getAttribute("id");
            result.put("identity",identity);
            result.put("id",id);
            result.put("currentUser",currentUser);
        }
        ResponseUtil.write(ServletActionContext.getResponse(),result);
        return null;
    }
    @Action(value = "DeleteSession")
    public String DeleteSession() throws Exception{
        HttpSession session=request.getSession();
        session.removeAttribute("currentUser");
        session.removeAttribute("identity");
        return null;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
