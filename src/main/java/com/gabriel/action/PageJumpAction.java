package com.gabriel.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

@Action(value = "PageJumpAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
@Results({
        @Result(name="articleJump",location = "/WEB-INF/super_admin/article.html"),
        @Result(name="userJump",location = "/WEB-INF/super_admin/user.html"),
        @Result(name="messageJump",location = "/WEB-INF/super_admin/message.html"),
        @Result(name="BackJump",location = "/WEB-INF/super_admin/main.html")
})
public class PageJumpAction extends ActionSupport {
    @Action(value = "ArticleJump")
    public String articleJump(){
        return "articleJump";
    }
    @Action(value = "UserJump")
    public String userJump(){
        return "userJump";
    }
    @Action(value = "MessageJump")
    public String messageJump(){
        return "messageJump";
    }
    @Action(value = "BackJump")
    public String BackJump(){
        return "BackJump";
    }
}
