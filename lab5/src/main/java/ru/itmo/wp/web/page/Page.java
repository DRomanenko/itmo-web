package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;
import ru.itmo.wp.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    private UserService userService = new UserService();
    private TalkService talkService = new TalkService();
    private EventService eventService = new EventService();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private HttpServletRequest pageRequest = null;

    void setMessage(String message) {
        pageRequest.getSession().setAttribute("message", message);
        throw new RedirectException("/index");
    }

    void setUser(User user) {
        pageRequest.getSession().setAttribute("user", user);
    }

    User getUser() {
        return (User) pageRequest.getSession().getAttribute("user");
    }

    UserService getUserService() {
        return userService;
    }

    UserRepository getUserRepository() {
        return userRepository;
    }

    TalkService getTalkService() {
        return talkService;
    }

    void action() {
        // No operations.
    }

    private void putUser(Map<String, Object> view) {
        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
    }

    void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    void before(HttpServletRequest request, Map<String, Object> view) {
        pageRequest = request;
        putUser(view);
        putMessage(request, view);
    }

    void after(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userRepository.findCount());
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}