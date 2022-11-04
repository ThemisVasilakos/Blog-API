package net.vasilakos.blogapi.repository;

import net.vasilakos.blogapi.model.Article;

public interface CustomArticleRepository {
    Article findArticleByCategoryAndTitle(String category, String title);
}
