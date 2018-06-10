package com.gabriel.dao.Impl;

import com.gabriel.dao.ArticleDao;
import com.gabriel.model.Article;
import com.gabriel.model.PageBean;
import com.gabriel.util.StringUtil;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    private SessionFactory sessionFactory;
    private static Logger logger = LogManager.getLogger(ArticleDaoImpl.class.getName());

    @Override
    public List<Article> articleList(PageBean pageBean, Article article) throws Exception {
        List<Article> articleList = null;
        Session session = this.getSession();
        StringBuffer stringBuffer = new StringBuffer("from Article ");
        if (article != null && StringUtil.isNotEmpty(article.getArticleTitle())) {
            stringBuffer.append(" a where a.articleTitle like '%" + article.getArticleTitle() + "%'");
        }
        stringBuffer.append(" order by articleId desc ");
        Query query = session.createQuery(stringBuffer.toString().replaceFirst("and", "where"));
        if (pageBean != null) {
            query.setFirstResult(pageBean.getStart());
            query.setMaxResults(pageBean.getRows());
        }
        articleList=(List<Article>)query.list();
        logger.info("查询到"+articleList.size()+"条文章");
        return articleList;
    }

    @Override
    public int articleCount(Article article) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("select count(*) as total from t_article");
        if (StringUtil.isNotEmpty(article.getArticleTitle())) {
            stringBuffer.append(" and article_title like '%" + article.getArticleTitle() + "%'");
        }
        Session session = this.getSession();
        Query query = session.createSQLQuery(stringBuffer.toString().replaceFirst("and", "where"));
        return ((BigInteger) query.uniqueResult()).intValue();
    }

    @Override
    public int articleDelete(String delIds) throws Exception {
        Session session = this.getSession();
        Query query = session.createSQLQuery("delete from t_article where article_id in(" + delIds + ")");
        int count = query.executeUpdate();
        logger.info("删除ID为："+delIds+"的文章！");
        return count;
    }

    @Override
    public int articleSave(Article article) throws Exception {
        Session session = this.getSession();
        session.merge(article);
        logger.info("保存文章《"+article.getArticleTitle()+"》成功！");
        return 1;
    }

    @Override
    public Article getArticleByArticleId(int articleId) throws Exception {
        Session session=this.getSession();
        Article article=(Article)session.get(Article.class,articleId);
        return article;
    }


    @Resource
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
