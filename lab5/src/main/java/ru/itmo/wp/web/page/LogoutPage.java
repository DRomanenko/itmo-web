package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class LogoutPage extends Page {
    @Override
    void before (HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    void after (HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

    private void action(HttpServletRequest request) {
        getEventService().logoutEvent(getUser());
        request.getSession().removeAttribute("user");
        setMessage("Good bye. Hope to see you soon!");
    }
}
