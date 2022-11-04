package net.vasilakos.blogapi.service;

import net.vasilakos.blogapi.dto.ArticleDTO;
import net.vasilakos.blogapi.dto.CommentDTO;
import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.Comment;
import net.vasilakos.blogapi.model.User;
import net.vasilakos.blogapi.repository.ArticleRepository;
import net.vasilakos.blogapi.repository.CommentRepository;
import net.vasilakos.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public Comment addComment(String body,Long articleId){
        Comment comment = new Comment();

        Article article = articleRepository.findById(articleId).get();

        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        User user = userRepository.findByUsername(username);

        comment.setUser(user);
        comment.setArticle(article);
        comment.setCommentBody(body);

        article.addComment(comment);

        return commentRepository.save(comment);
    }


    public ArrayList<CommentDTO> getArticleComments(Long articleId){
        ArrayList<Comment> comments = commentRepository.findCommentByArticleId(articleId);
        ArrayList<CommentDTO> commentsDTO = new ArrayList<>();

        for(int i=0;i<comments.size();i++){
            CommentDTO tmp = new CommentDTO(comments.get(i));
            commentsDTO.add(tmp);
        }

        return commentsDTO;
    }

}
