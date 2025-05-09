package dev.mfikri.servlet;

import dev.mfikri.servlet.model.SayHelloRequest;
import dev.mfikri.servlet.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/api/say-hello")
public class ApiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SayHelloRequest sayHelloRequest = JsonUtil.getObjectMapper().readValue(req.getReader(), SayHelloRequest.class);

        String sayHello = "Hello " + sayHelloRequest.getFirstName() + " " + sayHelloRequest.getLastName();

        Map<String, String> response = Map.of(
                "data", sayHello
        );

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(JsonUtil.getObjectMapper().writeValueAsString(response));

    }
}
