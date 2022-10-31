package net.vasilakos.blogapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String body;

    @Column
    private String category;

    @Column
    private Integer likes;

    @ManyToOne( cascade = {CascadeType.PERSIST})
    @JoinColumn(
            name="user_id",
            referencedColumnName = "userId"
    )
    private User user;

    @OneToMany( mappedBy = "article", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    public void addComment(Comment comment){
        if(comments==null) comments = new ArrayList<>();
        comments.add(comment);
    }

    public Article() {
        this.likes=0;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
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

    public List<Comment> getComments() {
        return comments;
    }
}
