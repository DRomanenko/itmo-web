package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        switch (uri) {
            case "/message/auth": {
                String user;
                if (session.getAttribute("user") == null) {
                    user = request.getParameter("user");
                    session.setAttribute("user", request.getParameter("user"));
                } else {
                    user = session.getAttribute("user").toString();
                }
                String json = new Gson().toJson(user);
                response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
                response.getWriter().flush();
                break;
            }
            case "/message/findAll": {
                String json = new Gson().toJson(messages);
                response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
                response.getOutputStream().flush();
                break;
            }
            case "/message/add":
                messages.add(new Message(session.getAttribute("user").toString(), request.getParameter("text")));
                break;
        }
        response.setContentType("application/json");
    }

    static class Message {
        String user, text;

        Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }

    private ArrayList<Message> messages = new ArrayList<>();
}
