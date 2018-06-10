package com.gabriel.action;

import com.gabriel.model.Article;
import com.gabriel.model.PageBean;
import com.gabriel.service.ArticleService;
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

@Action(value = "ArticleAction")
@Scope("prototype")
@Namespace("/")
public class ArticleAction extends ActionSupport {
    @Resource
    private ArticleService articleService;
    private String page;
    private String rows;
    private String a_articleTitle = "";
    private Article article;
    private String delIds;
    private String s_articleId;
    private String g_articleId;
    private String gabriel_articleTitle;
    private String gabriel_articleContent;

    public String getG_articleId() {
        return g_articleId;
    }

    public void setG_articleId(String g_articleId) {
        this.g_articleId = g_articleId;
    }

    public String getGabriel_articleTitle() {
        return gabriel_articleTitle;
    }

    public void setGabriel_articleTitle(String gabriel_articleTitle) {
        this.gabriel_articleTitle = gabriel_articleTitle;
    }

    public String getGabriel_articleContent() {
        return gabriel_articleContent;
    }

    public void setGabriel_articleContent(String gabriel_articleContent) {
        this.gabriel_articleContent = gabriel_articleContent;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
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

    public String getA_articleTitle() {
        return a_articleTitle;
    }

    public void setA_articleTitle(String a_articleTitle) {
        this.a_articleTitle = a_articleTitle;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getDelIds() {
        return delIds;
    }

    public void setDelIds(String delIds) {
        this.delIds = delIds;
    }

    public String getS_articleId() {
        return s_articleId;
    }

    public void setS_articleId(String s_articleId) {
        this.s_articleId = s_articleId;
    }

    @Action(value = "ArticleFindAction")
    public String list() throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        try {
            if (article == null) {
                article = new Article();
            }
            article.setArticleTitle(a_articleTitle);
            JSONObject result=new JSONObject();
            List<Article> articleList=articleService.articleList(pageBean,article);
            JSONArray jsonArray=new JSONArray();
            for (int i = 0; i < articleList.size(); i++) {
                Article article=(Article)articleList.get(i);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("articleId",article.getArticleId());
                jsonObject.put("articleTitle",article.getArticleTitle());
                jsonObject.put("articleDate", DateUtil.formatDate(article.getArticleDate(), "yyyy-MM-dd"));
                jsonObject.put("articleContent",article.getArticleContent());
                jsonArray.add(jsonObject);
            }
            int total=articleService.articleCount(article);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(ServletActionContext.getResponse(),result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Action(value = "ArticleDeleteAction")
    public String delete() throws Exception{
        try {
            JSONObject result=new JSONObject();
            String str[]=delIds.split(",");
            int delNums=articleService.articleDelete(delIds);
            if(delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else {
                result.put("errorMsg","删除失败");
            }
            ResponseUtil.write(ServletActionContext.getResponse(),result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Action(value = "ArticleSaveAction")
    public String save() throws Exception{
        if(StringUtil.isNotEmpty(s_articleId)){
            article.setArticleId(Integer.parseInt(s_articleId));
        }
        try{
            int saveNums=0;
            JSONObject result=new JSONObject();
            saveNums=articleService.articleSave(article);
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
    @Action(value = "ArticleComboListAction")
    public String userComboList(){
        try{
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("articleId", "");
            jsonObject.put("articleTitle", "请选择...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(jsonArray.fromObject(articleService.articleList(null,null)));
            ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Action(value = "GabrielSaveArticle")
    public String gabrielSaveArticle(){
        Article g_article=new Article();
        if(StringUtil.isNotEmpty(s_articleId)){
            g_article.setArticleId(Integer.parseInt(s_articleId));
        }
        g_article.setArticleTitle(gabriel_articleTitle);
        g_article.setArticleContent(gabriel_articleContent);
        Date datedate=new Date();
        g_article.setArticleDate(datedate);
       try{
           int ssNums=0;
           JSONObject result=new JSONObject();
           ssNums=  articleService.articleSave(g_article);
           if(ssNums>0){
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
    @Action(value = "GabrielGetArticleById")
    public String getArticleById(){
        try {
            Article article=articleService.getArticleByArticleId(Integer.parseInt(g_articleId));
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("articleId",article.getArticleId());
            jsonObject.put("articleTitle",article.getArticleTitle());
            jsonObject.put("articleDate", DateUtil.formatDate(article.getArticleDate(), "yyyy-MM-dd"));
            jsonObject.put("articleContent",article.getArticleContent());
            ResponseUtil.write(ServletActionContext.getResponse(),jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
