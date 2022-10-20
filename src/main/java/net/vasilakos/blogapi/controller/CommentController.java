package net.vasilakos.blogapi.controller;

import net.vasilakos.blogapi.dto.CommentDTO;
import net.vasilakos.blogapi.model.Comment;
import net.vasilakos.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/blog/articles")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{articleId}/comment")
    public ResponseEntity<?> makeComment(@RequestBody String commentBody, @PathVariable Long articleId){
        return ResponseEntity.ok(commentService.addComment(commentBody,articleId));
    }

    @GetMapping("/{articleId}/comments")
    public ArrayList<CommentDTO> getArticleComments(@PathVariable Long articleId){
        return commentService.getArticleComments(articleId);
    }
}
