package net.vasilakos.blogapi.repository;

import net.vasilakos.blogapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "select * from article\n" +
            "inner join user on article.user_id=user.user_id where username=?1 and article_id=?2",nativeQuery = true)
    Article findArticleByUsernameAndArticleId(String username,Long article_id);

    ArrayList<Article> findArticleByCategory(String category);

    ArrayList<Article> findArticleByTitleContaining(String title);
}
