package com.gabriel.action;

import com.gabriel.model.Article;
import com.gabriel.model.Message;
import com.gabriel.model.PageBean;
import com.gabriel.model.User;
import com.gabriel.service.ArticleService;
import com.gabriel.service.MessageService;
import com.gabriel.service.UserService;
import com.gabriel.util.DateUtil;
import com.gabriel.util.ResponseUtil;
import com.gabriel.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Action(value = "MessageAction")
@Scope("prototype")
@Namespace("/")
public class MessageAction extends ActionSupport {
    @Resource
    private MessageService messageService;
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;
    private String page;
    private String rows;
    private String messageContent;
    private Message message;
    private String delIds;
    private Article article;
    private String messageId;
    private User user;

    private String s_mUserId;

    private String UMessageContent;
    private String UMid;

    public String getUMessageContent() {
        return UMessageContent;
    }

    public void setUMessageContent(String UMessageContent) {
        this.UMessageContent = UMessageContent;
    }

    public String getUMid() {
        return UMid;
    }

    public void setUMid(String UMid) {
        this.UMid = UMid;
    }

    public String getS_mUserId() {
        return s_mUserId;
    }

    public void setS_mUserId(String s_mUserId) {
        this.s_mUserId = s_mUserId;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getDelIds() {
        return delIds;
    }

    public void setDelIds(String delIds) {
        this.delIds = delIds;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Action(value = "MessageFindAction")
    public String list() throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        try {
            if (message == null) {
                message = new Message();
            }
            message.setMessageContent(messageContent);
            JSONObject result = new JSONObject();
            List<Message> messageList = messageService.messageList(pageBean, message);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < messageList.size(); i++) {
                Message message = (Message) messageList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("messageId", message.getMessageId());
                jsonObject.put("messageContent", message.getMessageContent());
                jsonObject.put("messageDate", DateUtil.formatDate(message.getMessageDate(), "yyyy-MM-dd"));
                user = userService.getUserById(message.getmUserId());
                jsonObject.put("UserName", user.getUserName());
                jsonObject.put("UserId", user.getId());
                jsonArray.add(jsonObject);
            }
            int total = messageService.messageCount(message);
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(ServletActionContext.getResponse(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "MessageDeleteAction")
    public String delete() throws Exception {
        try {
            JSONObject result = new JSONObject();
            String str[] = delIds.split(",");
            int delNums = messageService.messageDelete(delIds);
            if (delNums > 0) {
                result.put("success", "true");
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除失败");
            }
            ResponseUtil.write(ServletActionContext.getResponse(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "MessageSaveAction")
    public String save() throws Exception {
        if (StringUtil.isNotEmpty(messageId)){
            message.setMessageId(Integer.parseInt(messageId));
        }try {
            int saveNums=0;
            JSONObject result=new JSONObject();
            saveNums=messageService.messageSave(message);
            if(saveNums>0){
                result.put("success","true");
            }else {
                result.put("success","true");
                result.put("errorMsg","保存失败");
            }
            ResponseUtil.write(ServletActionContext.getResponse(),result);
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
    }
    @Action(value = "UMessageSaveAction")
    public String USave() throws Exception{
        Message message=new Message();
        message.setMessageContent(UMessageContent);
        message.setmUserId(Integer.parseInt(UMid));
        Date datedate=new Date();
        message.setMessageDate(datedate);
        int saveNums=0;
        JSONObject result=new JSONObject();
        saveNums=messageService.messageSave(message);
        if(saveNums>0){
            result.put("success","true");
        }else {
            result.put("success","true");
            result.put("errorMsg","保存失败");
        }
        ResponseUtil.write(ServletActionContext.getResponse(),result);
        return null;
    }
}
