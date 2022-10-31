package net.vasilakos.blogapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesId;

    @ManyToOne( cascade = {CascadeType.PERSIST})
    @JoinColumn(
            name="article_id",
            referencedColumnName = "articleId"
    )
    private Article article;

    @ManyToOne( cascade = {CascadeType.PERSIST})
    @JsonIgnore
    @JoinColumn(
            name="user_id",
            referencedColumnName = "userId"
    )
    private User user;

    public Likes() {
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
