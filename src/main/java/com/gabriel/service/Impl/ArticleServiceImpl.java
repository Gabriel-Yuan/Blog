package com.gabriel.service.Impl;

import com.gabriel.dao.ArticleDao;
import com.gabriel.model.Article;
import com.gabriel.model.PageBean;
import com.gabriel.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public List<Article> articleList(PageBean pageBean, Article article) throws Exception {
        return articleDao.articleList(pageBean,article);
    }

    @Override
    public int articleCount(Article article) throws Exception {
        return articleDao.articleCount(article);
    }

    @Override
    public int articleDelete(String delIds) throws Exception {
        return articleDao.articleDelete(delIds);
    }

    @Override
    public int articleSave(Article article) throws Exception {
        return articleDao.articleSave(article);
    }

    @Override
    public Article getArticleByArticleId(int articleId) throws Exception {
        return articleDao.getArticleByArticleId(articleId);
    }

}
