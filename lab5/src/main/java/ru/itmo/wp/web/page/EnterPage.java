package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;



/** @noinspection unused*/
public class EnterPage extends Page {
    private final UserService userService = new UserService();

    @Override
    void before (HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    void after (HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }


    private void enter(HttpServletRequest request) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");

        User user = userService.validateAndFindByLoginOrEmailAndPassword(loginOrEmail, password);
        setUser(user);
        getEventService().enterEvent(user);
        setMessage("Hello, " + user.getLogin());
        throw new RedirectException("/index");
    }
}
