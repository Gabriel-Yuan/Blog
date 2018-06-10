package com.gabriel.action;


import com.gabriel.model.PageBean;
import com.gabriel.model.Photo;
import com.gabriel.service.PhotoService;
import com.gabriel.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


@Action(value = "PhotoAction")
@Scope("prototype")
@Namespace("/")

public class PhotoAction extends ActionSupport implements ServletRequestAware {

    @Resource
    private PhotoService photoService;
    private HttpServletRequest request;
    private File fish_file;
    private String fish_fileFileName;  //文件名
    private String fish_fileContentType; //文件类型
    private String page;
    private String rows;
    private String delIds;
    private String Gid;

    public String getGid() {
        return Gid;
    }

    public void setGid(String gid) {
        Gid = gid;
    }

    public String getDelIds() {
        return delIds;
    }

    public void setDelIds(String delIds) {
        this.delIds = delIds;
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

    public File getFish_file() {
        return fish_file;
    }

    public void setFish_file(File fish_file) {
        this.fish_file = fish_file;
    }

    public String getFish_fileFileName() {
        return fish_fileFileName;
    }

    public void setFish_fileFileName(String fish_fileFileName) {
        this.fish_fileFileName = fish_fileFileName;
    }

    public String getFish_fileContentType() {
        return fish_fileContentType;
    }

    public void setFish_fileContentType(String fish_fileContentType) {
        this.fish_fileContentType = fish_fileContentType;
    }

    @Action(value = "PhotoList")
    public String PhotoList() throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        try {
            List<Photo> photoList = photoService.photoList(pageBean);
            JSONObject result = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < photoList.size(); i++) {
                Photo photo = (Photo) photoList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("pid", photo.getPid());
                jsonObject.put("pName", photo.getpName());
                jsonObject.put("pPath", photo.getpPath());
                jsonArray.add(jsonObject);
            }
            int total = photoService.photoCount();
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(ServletActionContext.getResponse(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "PhotoUpload")
    public String PhotoUpload() throws Exception {
        String path = "E:\\WorkSpace\\IdeaWorkSpace\\Blog\\src\\main\\webapp\\uploadImages";
        String npath = request.getServletContext().getRealPath("/uploadImages");
//        System.out.println(npath);
        if (fish_file != null) {
            File saveFile = new File(new File(path), fish_fileFileName);
            File nsaveFile = new File(new File(npath), fish_fileFileName);
            if (!saveFile.getParentFile().exists()) saveFile.getParentFile().mkdirs();
            if (!nsaveFile.getParentFile().exists()) nsaveFile.getParentFile().mkdirs();
            FileUtils.copyFile(fish_file, saveFile);
            FileUtils.copyFile(fish_file, nsaveFile);
            Photo photo = new Photo();
            photo.setpPath(path);
            photo.setpName(fish_fileFileName);
            photoService.photoSave(photo);
        }
        JSONObject result = new JSONObject();
        result.put("status", true);
        ResponseUtil.write(ServletActionContext.getResponse(), result);
        return null;
    }

    @Action(value = "PhotoDelete")
    public String PhotoDelete() throws Exception {
        String path = "E:\\WorkSpace\\IdeaWorkSpace\\Blog\\src\\main\\webapp\\uploadImages";
        String npath = request.getServletContext().getRealPath("/uploadImages");
        File deleteFile=new File(new File(path),photoService.GetPhotoById(Integer.parseInt(delIds)).getpName());
        File ndeleteFile=new File(new File(npath),photoService.GetPhotoById(Integer.parseInt(delIds)).getpName());
        if(deleteFile.exists()){
            deleteFile.delete();
            ndeleteFile.delete();
        }
        try {
            JSONObject result = new JSONObject();
            String str[] = delIds.split(",");
            int delNums = photoService.photoDelete(delIds);
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

    @Action(value = "GetPhotoById")
    public String GetPhotoById() throws Exception {
        try {
            Photo photo = photoService.GetPhotoById(Integer.parseInt(Gid));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", photo.getPid());
            jsonObject.put("pName", photo.getpName());
            jsonObject.put("pPath", photo.getpPath());
            ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
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
