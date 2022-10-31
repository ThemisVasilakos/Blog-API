package net.vasilakos.blogapi.controller;

import net.vasilakos.blogapi.model.Likes;
import net.vasilakos.blogapi.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/blog/articles")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @GetMapping("/favorites")
    public ArrayList<Likes> getMyLikedArticles(){
        return likesService.getLikedArticles();
    }

}
