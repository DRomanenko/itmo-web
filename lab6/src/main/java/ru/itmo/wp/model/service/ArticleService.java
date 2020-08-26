package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void validatePost(Article article) throws ValidationException {
        if (userRepository.find(article.getUserId()) == null) {
            throw new ValidationException("Author not specified");
        }
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required");
        }
        if (article.getTitle().length() > 256) {
            throw new ValidationException("Title should not be longer than 256 characters");
        }
        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required");
        }
        if (article.getText().length() > 1024) {
            throw new ValidationException("Text should not be longer than 1024 characters");
        }
    }

    public void validateAuthor(long id, User user) throws ValidationException {
        Article article = find(id);
        if (article.getUserId() != user.getId()) {
            throw new ValidationException("Only author is allowed to hide/publish his articles");
        }
    }

    public void setHidden(long id, boolean hidden) {
        articleRepository.setHidden(id, hidden);
    }

    public void post(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAllChronologicallyNotHidden() {
        return articleRepository.findAllChronologicallyNotHidden();
    }

    public List<Article> findAllByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }
}
