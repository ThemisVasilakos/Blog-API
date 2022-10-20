package net.vasilakos.blogapi.repository;

import net.vasilakos.blogapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "select * from comment where article_id=?1",nativeQuery = true)
    ArrayList<Comment> findCommentByArticleId(Long articleId);
}
