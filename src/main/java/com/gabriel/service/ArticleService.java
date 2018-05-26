package com.gabriel.service;

import com.gabriel.model.Article;
import com.gabriel.model.PageBean;

import java.util.List;

public interface ArticleService {
    public List<Article> articleList(PageBean pageBean, Article article) throws Exception;
    public int articleCount(Article article) throws Exception;
    public int articleDelete(String delIds) throws Exception;
    public int articleSave(Article article) throws Exception;
    public Article getArticleByArticleId(int articleId) throws Exception;
}
