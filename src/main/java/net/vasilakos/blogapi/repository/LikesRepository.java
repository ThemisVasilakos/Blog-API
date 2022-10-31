package net.vasilakos.blogapi.repository;

import net.vasilakos.blogapi.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    @Query(value = "select * from likes where user_id=?1 and article_id=?2",nativeQuery = true)
    Likes findByArticleIdAndUserId(Long userId,Long articleId);

    @Query(value = "select * from likes where user_id=?1",nativeQuery = true)
    ArrayList<Likes> findByUserId(Long userId);
}
