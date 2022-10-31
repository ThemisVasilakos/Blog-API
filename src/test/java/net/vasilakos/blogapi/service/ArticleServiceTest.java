package net.vasilakos.blogapi.service;

import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.User;
import net.vasilakos.blogapi.repository.ArticleRepository;
import net.vasilakos.blogapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveArticle() {
        User user = new User();
        user.setFirstName("Tester");
        user.setLastName("Testing");
        user.setEmail("test@email.com");
        user.setUsername("test");
        user.setPassword("1234");
        user.setRole("ROLE_ADMIN");

        Article article = new Article();
        article.setUser(user);
        article.setTitle("Test Article");
        article.setDescription("Test description");
        article.setCategory("testing");
        article.setBody("Test Body");

        articleRepository.save(article);

    }


}