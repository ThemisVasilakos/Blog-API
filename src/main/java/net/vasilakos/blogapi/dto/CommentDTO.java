package net.vasilakos.blogapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.Comment;
import net.vasilakos.blogapi.model.User;

public class CommentDTO {
    private String commentBody;
    private User user;

    @JsonIgnore
    private Article article;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        this.commentBody=comment.getCommentBody();
        this.user=comment.getUser();
        this.article=comment.getArticle();
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
