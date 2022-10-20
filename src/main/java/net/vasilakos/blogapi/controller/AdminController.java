package net.vasilakos.blogapi.controller;

import net.vasilakos.blogapi.dto.UserDTO;
import net.vasilakos.blogapi.service.ArticleService;
import net.vasilakos.blogapi.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/blog/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CustomUserDetailService userDetailService;

    @GetMapping("users/get-all")
    public ArrayList<UserDTO> getAllUsers(){
        return userDetailService.getAllUsers();
    }

    @DeleteMapping("/articles/delete/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId){
        articleService.deleteArticleAdmin(articleId);
        return new ResponseEntity<String>("Article deleted", HttpStatus.OK);
    }

}
