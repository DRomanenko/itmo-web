package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class UsersPage {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user",
                userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void setAdmin(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user;
        if ((user = (User) request.getSession().getAttribute("user")) == null) {
            request.getSession().setAttribute("message", "This domain is available for authorized users only.");
            throw new RedirectException("/index");
        }
        long requestUserId = user.getId();
        long targetUserId = Long.parseLong(request.getParameter("id"));
        boolean value = Long.parseLong(request.getParameter("value")) == 1;

        userService.validateAdmin(requestUserId);
        userService.setAdmin(targetUserId, value);
    }
}
