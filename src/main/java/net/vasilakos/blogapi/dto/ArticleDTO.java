package net.vasilakos.blogapi.dto;

import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.User;

public class ArticleDTO {
    private String title;

    private String description;

    private String body;

    private String category;

    private User user;

    public ArticleDTO() {
    }

    public ArticleDTO(Article article){
        this.title = article.getTitle();
        this.category = article.getCategory();
        this.description = article.getDescription();
        this.body = article.getBody();
        this.user = article.getUser();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
