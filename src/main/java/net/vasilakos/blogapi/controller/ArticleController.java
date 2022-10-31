package net.vasilakos.blogapi.controller;

import net.vasilakos.blogapi.dto.ArticleDTO;
import net.vasilakos.blogapi.service.ArticleService;
import net.vasilakos.blogapi.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LikesService likesService;

    @PostMapping("/new-article")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO){
        return ResponseEntity.ok(articleService.saveArticle(articleDTO));
    }

    @DeleteMapping("/{article_id}/delete")
    public ResponseEntity<String> deleteArticle(@PathVariable Long article_id){
        articleService.deleteArticle(article_id);
        return new ResponseEntity<String>("Article deleted.",HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public List<ArticleDTO> getAllArticles(){
        return articleService.getAll();
    }

    @GetMapping("/search-by-category/{category}")
    public List<ArticleDTO> searchArticleByCategory(@PathVariable String category){
        return articleService.searchByCategory(category);
    }

    @GetMapping("/search-by-title/{title}")
    public List<ArticleDTO> searchArticleByTitle(@PathVariable String title){
        return articleService.searchByTitle(title);
    }

    @PostMapping("/{articleId}/like")
    public String likeArticle(@PathVariable Long articleId){
        return likesService.likeArticle(articleId);
    }
}
