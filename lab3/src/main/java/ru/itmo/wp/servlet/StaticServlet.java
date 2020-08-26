package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Begin 1,2 tasks */
        String uri = request.getRequestURI();
        String delimiter = "\\+";
        String[] subStr = uri.split(delimiter);

        OutputStream outputStream = response.getOutputStream();
        boolean flag = false;
        for (String currURI : subStr) {
            File file = new File(getServletContext().getRealPath("."), Paths.get("../../src/main/webapp/static", currURI).toString());
            if (!file.isFile()) {
                file = new File(getServletContext().getRealPath("/static"), Paths.get(currURI).toString());
            }

            if (file.isFile()) {
                if (!flag) {
                    response.setContentType(getContentTypeFromName(currURI));
                    flag = true;
                }
                Files.copy(file.toPath(), outputStream);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        outputStream.flush();
        /* End 1,2 tasks */
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
