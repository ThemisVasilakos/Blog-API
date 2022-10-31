package net.vasilakos.blogapi.service;

import net.vasilakos.blogapi.dto.ArticleDTO;
import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.User;
import net.vasilakos.blogapi.repository.ArticleRepository;
import net.vasilakos.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    public Article saveArticle(ArticleDTO articleDTO){
        Article article = new Article();

        article.setTitle(articleDTO.getTitle());
        article.setCategory(articleDTO.getCategory());
        article.setDescription(articleDTO.getDescription());
        article.setBody(articleDTO.getBody());

        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        User user = userRepository.findByUsername(username);

        article.setUser(user);

        return articleRepository.save(article);
    }

    @Transactional
    public ArticleDTO updateArticle(ArticleDTO articleDTO,Long article_id){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        Article article = articleRepository.findArticleByUsernameAndArticleId(username,article_id);

        article.setTitle(articleDTO.getTitle());
        article.setCategory(articleDTO.getCategory());
        article.setDescription(articleDTO.getDescription());
        article.setBody(articleDTO.getBody());

        articleRepository.save(article);

        return articleDTO;
    }

    public void deleteArticle(Long article_id){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        Article article = articleRepository.findArticleByUsernameAndArticleId(username,article_id);

        articleRepository.delete(article);
    }

    public void deleteArticleAdmin(Long article_id){
        articleRepository.deleteById(article_id);
    }

    public ArrayList<ArticleDTO> getAll(){
        ArrayList<Article> articles = (ArrayList<Article>) articleRepository.findAll();
        ArrayList<ArticleDTO> articleDTOList = new ArrayList<>();

        for(int i=0;i<articles.size();i++){
            ArticleDTO tmp = new ArticleDTO(articles.get(i));
            articleDTOList.add(tmp);
        }
        return articleDTOList;
    }

    public ArrayList<ArticleDTO> searchByCategory(String category){
        ArrayList<Article> articles = (ArrayList<Article>) articleRepository.findArticleByCategory(category);
        ArrayList<ArticleDTO> articleDTOList = new ArrayList<>();

        for(int i=0;i<articles.size();i++){
            ArticleDTO tmp = new ArticleDTO(articles.get(i));
            articleDTOList.add(tmp);
        }
        return articleDTOList;
    }

    public ArrayList<ArticleDTO> searchByTitle(String title){
        ArrayList<Article> articles = (ArrayList<Article>) articleRepository.findArticleByTitleContaining(title);
        ArrayList<ArticleDTO> articleDTOList = new ArrayList<>();

        for(int i=0;i<articles.size();i++){
            ArticleDTO tmp = new ArticleDTO(articles.get(i));
            articleDTOList.add(tmp);
        }
        return articleDTOList;
    }

    public Optional<Article> findByID(Long articleId){
        return articleRepository.findById(articleId);
    }


}
