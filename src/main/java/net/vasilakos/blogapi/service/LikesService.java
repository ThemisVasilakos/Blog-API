package net.vasilakos.blogapi.service;

import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.Likes;
import net.vasilakos.blogapi.model.User;
import net.vasilakos.blogapi.repository.ArticleRepository;
import net.vasilakos.blogapi.repository.LikesRepository;
import net.vasilakos.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public String likeArticle(Long articleId){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        Long id = userRepository.findUserIdByUsername(username);

        Likes check = likesRepository.findByArticleIdAndUserId(id,articleId);
        if(check!=null){
            return "User already liked the article";
        }

        User user = userRepository.findByUsername(username);
        Article article = articleRepository.findById(articleId).get();

        Likes likes = new Likes();
        likes.setUser(user);
        likes.setArticle(article);

        int num = article.getLikes() + 1;
        article.setLikes(num);
        articleRepository.save(article);

        likesRepository.save(likes);

        return "Article liked!";
    }

    public ArrayList<Likes> getLikedArticles(){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();
        Long id = userRepository.findUserIdByUsername(username);

        return likesRepository.findByUserId(id);
    }

}
