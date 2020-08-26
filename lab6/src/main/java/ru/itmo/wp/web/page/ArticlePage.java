package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "This domain is available for authorized users only.");
            throw new RedirectException("/index");
        }
    }

    private void submit(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "This domain is available for authorized users only.");
            throw new RedirectException("/index");
        }

        Article article = new Article();
        article.setUserId(((User) request.getSession().getAttribute("user")).getId());
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));
        article.setHidden(false);

        articleService.validatePost(article);
        articleService.post(article);

        request.getSession().setAttribute("message", "Article #" + article.getId() + " has been successfully published.");
        throw new RedirectException("/index");
    }
}
