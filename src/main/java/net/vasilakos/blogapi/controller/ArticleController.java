package net.vasilakos.blogapi.controller;

import net.vasilakos.blogapi.dto.ArticleDTO;
import net.vasilakos.blogapi.repository.CustomArticleRepository;
import net.vasilakos.blogapi.service.ArticleService;
import net.vasilakos.blogapi.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LikesService likesService;

    @PostMapping("/articles")
    public ResponseEntity<Long> createArticle(@RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<Long>(articleService.saveArticle(articleDTO),HttpStatus.CREATED);
    }

    @PutMapping("/articles/{articleId}")
    public ResponseEntity<Long> editArticle(@RequestBody ArticleDTO articleDTO,@PathVariable Long articleId){
        return new ResponseEntity<Long>(articleService.updateArticle(articleDTO,articleId),HttpStatus.OK);
    }

    @DeleteMapping("/articles/{article_id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long article_id){
        articleService.deleteArticle(article_id);
        return new ResponseEntity<String>("Article deleted!",HttpStatus.OK);
    }

    @GetMapping("/articles")
    public List<ArticleDTO> getAllArticles(){
        return articleService.getAll();
    }

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long articleId) throws Exception {
        return new ResponseEntity<ArticleDTO>(articleService.findByID(articleId),HttpStatus.OK);
    }

    @PostMapping("/{articleId}/like")
    public String likeArticle(@PathVariable Long articleId){
        return likesService.likeArticle(articleId);
    }


    @GetMapping("/articles/search")
    public ArticleDTO searchArticleByTitleAndCategory(@RequestParam String category,@RequestParam(required = false) String title){
        return articleService.searchByCategoryAndTitle(category,title);
    }


}
