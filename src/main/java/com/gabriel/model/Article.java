package com.gabriel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_article", schema = "blog")
public class Article {
    private int articleId;
    private String articleTitle;
    private Date articleDate;
    private String articleContent;

    public Article(int articleId, String articleTitle, Date articleDate, String articleContent) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleDate = articleDate;
        this.articleContent = articleContent;
    }

    public Article() {
    }

    @Id
    @Column(name = "article_id")
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "article_title")
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Basic
    @Column(name = "article_date")
    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    @Basic
    @Column(name = "article_content")
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (articleId != article.articleId) return false;
        if (articleTitle != null ? !articleTitle.equals(article.articleTitle) : article.articleTitle != null)
            return false;
        if (articleDate != null ? !articleDate.equals(article.articleDate) : article.articleDate != null) return false;
        if (articleContent != null ? !articleContent.equals(article.articleContent) : article.articleContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleId;
        result = 31 * result + (articleTitle != null ? articleTitle.hashCode() : 0);
        result = 31 * result + (articleDate != null ? articleDate.hashCode() : 0);
        result = 31 * result + (articleContent != null ? articleContent.hashCode() : 0);
        return result;
    }
}
