package org.cypmaster.servlets;

import org.cypmaster.services.LoginService;
import org.cypmaster.utils.PersonalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"}, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

    private LoginService loginService;
    private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    private final static String USERNAME_FORM_NAME = "username";
    private final static String USERNAME_SESSION_NAME = "username";
    private final static String PASSWORD_FORM_NAME = "password";
    private final static String INPUT_PAGE_LOCATION = "http://localhost:8080/input";
    private final static String HOME_PAGE_LOCATION = "http://localhost:8080/home";
    private final static String LOGIN_PAGE_LOCATION = "/login.jsp";

    @Override
    public void init() throws ServletException {
        loginService = LoginService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_PAGE_LOCATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> username = Optional.of(req.getParameter(USERNAME_FORM_NAME));
        Optional<String> password = Optional.of(req.getParameter(PASSWORD_FORM_NAME));
        if (!username.isPresent() || !password.isPresent()) {
            logger.info("Not all fields were completed");
            throw new PersonalException("Complete all fields");
        }

        boolean goodCredentials = loginService.checkCredentials(username.get(), password.get());
        if (!goodCredentials) {
            logger.info("Wrong credentials");
            req.setAttribute("message", "Wrong Credentials");
            req.getRequestDispatcher(LOGIN_PAGE_LOCATION).forward(req, resp);
            return;
        }

        logger.info("User:%s was authenticated", username.get());
        HttpSession session = req.getSession(true);
        session.setAttribute(USERNAME_SESSION_NAME, username.get());

        resp.sendRedirect(HOME_PAGE_LOCATION);

    }
}
