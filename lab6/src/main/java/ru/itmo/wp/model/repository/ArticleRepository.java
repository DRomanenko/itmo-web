package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Article find(long id);
    List<Article> findAllChronologicallyNotHidden();
    List<Article> findAllByUserId(long userId);
    void setHidden(long id, boolean hidden);
    void save(Article article);
}
