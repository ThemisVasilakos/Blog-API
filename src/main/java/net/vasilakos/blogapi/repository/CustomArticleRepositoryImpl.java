package net.vasilakos.blogapi.repository;

import net.vasilakos.blogapi.model.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomArticleRepositoryImpl implements CustomArticleRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Article findArticleByCategoryAndTitle(String category, String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Article> cq = cb.createQuery(Article.class);

        Root<Article> article = cq.from(Article.class);
        List<Predicate> predicates = new ArrayList<>();

        if (category != null) {
            predicates.add(cb.equal(article.get("category"), category));
        }
        if (title != null) {
            predicates.add(cb.like(article.get("title"), "%" + title + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getSingleResult();
    }

}
