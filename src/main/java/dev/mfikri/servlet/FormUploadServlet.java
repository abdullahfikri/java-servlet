package dev.mfikri.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet("/form-upload")
@MultipartConfig
public class FormUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of(FormUploadServlet.class.getResource("/html/form-upload.html").getPath());
        String html = Files.readString(path);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        Part profile = req.getPart("profile");

        System.out.println(profile.getSubmittedFileName());
        Path uploadLocation = Path.of("upload/" + UUID.randomUUID() + profile.getSubmittedFileName());
        Files.copy(profile.getInputStream(), uploadLocation);

        String html = """
                <html>
                    <head>
                        <title>HTML RESPONSE</title>
                    </head>
                    <body>
                        <p>Name : $name</p> <br>
                        <p>Profile :</p> <img width="400px" height="400px" src="/download?file=$profile" />
                    </body>
                </html>
                """
                .replace("$name", fullName)
                .replace("$profile", uploadLocation.getFileName().toString());

        resp.getWriter().println(html);
    }
}
