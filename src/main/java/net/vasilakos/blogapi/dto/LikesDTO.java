package net.vasilakos.blogapi.dto;

import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.Likes;
import net.vasilakos.blogapi.model.User;

public class LikesDTO {
    private Article article;

    private User user;

    public LikesDTO() {
    }

    public LikesDTO(Likes likes) {
        this.article = likes.getArticle();
        this.user = likes.getUser();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
