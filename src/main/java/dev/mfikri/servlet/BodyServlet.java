package dev.mfikri.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/body")
public class BodyServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = """
                <html>
                    <head>
                        <title>HTML RESPONSE</title>
                    </head>
                    <body>
                        <h1>Muhammad Fikri</h1>
                    </body>
                </html>
                """;
        resp.getWriter().println(html);
    }
}
