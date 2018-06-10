package com.gabriel.action;


import com.gabriel.model.PageBean;
import com.gabriel.model.User;
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
import java.util.List;

@Action(value = "UserAction")
@Scope("prototype")
@Namespace("/")
public class UserAction extends ActionSupport {
    @Resource
    private UserService userService;
    private String page;
    private String rows;
    private String u_userName = "";
    private User user;
    private String delIds;
    private String id;

    private String GuserName;
    private String Gpassword;
    private String Gsex;
    private String Gemail;
    private String Gbirthday;
    private String Gdesc;
    private String Gid;

    public String getGid() {
        return Gid;
    }

    public void setGid(String gid) {
        Gid = gid;
    }

    public String getGuserName() {
        return GuserName;
    }

    public void setGuserName(String guserName) {
        GuserName = guserName;
    }

    public String getGpassword() {
        return Gpassword;
    }

    public void setGpassword(String gpassword) {
        Gpassword = gpassword;
    }

    public String getGsex() {
        return Gsex;
    }

    public void setGsex(String gsex) {
        Gsex = gsex;
    }

    public String getGemail() {
        return Gemail;
    }

    public void setGemail(String gemail) {
        Gemail = gemail;
    }

    public String getGbirthday() {
        return Gbirthday;
    }

    public void setGbirthday(String gbirthday) {
        Gbirthday = gbirthday;
    }

    public String getGdesc() {
        return Gdesc;
    }

    public void setGdesc(String gdesc) {
        Gdesc = gdesc;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public String getU_userName() {
        return u_userName;
    }

    public void setU_userName(String u_userName) {
        this.u_userName = u_userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDelIds() {
        return delIds;
    }

    public void setDelIds(String delIds) {
        this.delIds = delIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Action(value = "UserFindAction")
    public String list() throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        try {
            if (user == null) {
                user = new User();
            }
            user.setUserName(u_userName);
            JSONObject result = new JSONObject();
            List<User> userList = userService.userList(pageBean, user);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < userList.size(); i++) {
                User user = (User) userList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", user.getId());
                jsonObject.put("password", user.getPassword());
                jsonObject.put("userName", user.getUserName());
                jsonObject.put("sex", user.getSex());
                jsonObject.put("birthday", DateUtil.formatDate(user.getBirthday(), "yyyy-MM-dd"));
                jsonObject.put("email", user.getEmail());
                jsonObject.put("userDesc", user.getUserDesc());
                jsonArray.add(jsonObject);
            }
            int total = userService.userCount(user);
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(ServletActionContext.getResponse(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "UserDeleteAction")
    public String delete() throws Exception {
        try {
            JSONObject result = new JSONObject();
            String str[] = delIds.split(",");
            int delNums = userService.userDelete(delIds);
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

    @Action(value = "UserSaveAction")
    public String save() throws Exception {
        if (StringUtil.isNotEmpty(id)) {
            user.setId(Integer.parseInt(id));
        }
        try {
            int saveNums = 0;
            JSONObject result = new JSONObject();
            saveNums = userService.userSave(user);
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(ServletActionContext.getResponse(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "UserComboListAction")
    public String userComboList() {
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", "");
            jsonObject.put("userName", "请选择...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(jsonArray.fromObject(userService.userList(null, null)));
            ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "UserRegisterAction")
    public String userRegister() throws Exception {
        User user = new User();
        user.setUserName(GuserName);
        user.setPassword(Gpassword);
        user.setBirthday(DateUtil.formatString(Gbirthday, "yyyy-MM-dd"));
        user.setSex(Gsex);
        user.setEmail(Gemail);
        if (StringUtil.isNotEmpty(Gdesc)) {
            user.setUserDesc(Gdesc);
        }
        int saveNums = 0;
        JSONObject result = new JSONObject();
        saveNums = userService.userSave(user);
        if (saveNums > 0) {
            result.put("success", "true");
        } else {
            result.put("success", "false");
            result.put("errorMsg", "注册失败");
        }
        ResponseUtil.write(ServletActionContext.getResponse(), result);
        return null;
    }
    @Action(value = "UserChangeAction")
    public String userChange() throws Exception {
        User user = new User();
        user.setId(Integer.parseInt(Gid));
        user.setUserName(GuserName);
        user.setPassword(Gpassword);
        user.setBirthday(DateUtil.formatString(Gbirthday, "yyyy-MM-dd"));
        user.setSex(Gsex);
        user.setEmail(Gemail);
        if (StringUtil.isNotEmpty(Gdesc)) {
            user.setUserDesc(Gdesc);
        }
        int saveNums = 0;
        JSONObject result = new JSONObject();
        saveNums = userService.userSave(user);
        if (saveNums > 0) {
            result.put("success", "true");
        } else {
            result.put("success", "false");
            result.put("errorMsg", "保存失败");
        }
        ResponseUtil.write(ServletActionContext.getResponse(), result);
        return null;
    }
    @Action(value = "GetUserByIdAction")
    public String GetUserById() throws Exception {
        System.out.println(Gid);
        User user=userService.getUserById(Integer.parseInt(Gid));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("userName", user.getUserName());
        jsonObject.put("sex", user.getSex());
        jsonObject.put("birthday", DateUtil.formatDate(user.getBirthday(), "yyyy-MM-dd"));
        jsonObject.put("email", user.getEmail());
        jsonObject.put("userDesc", user.getUserDesc());
        ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
        return null;
    }
}
