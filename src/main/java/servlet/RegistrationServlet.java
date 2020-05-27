package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegistrationServlet extends HttpServlet {
    private static final String HTML_DIR = "templates";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder str = new StringBuilder();
        Files.lines(Paths.get(HTML_DIR + File.separator + "registerPage.html")).
                forEach(s -> str.append(s).append(" "));
        resp.getWriter().println(str);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getUserService();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(userService.addUser(new User(email, password)));



    }
}
