package ru.itmo.wp.web.page;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class IndexPage extends Page {
    @Override
    void before (HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    void after (HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

    /*private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }*/
}
