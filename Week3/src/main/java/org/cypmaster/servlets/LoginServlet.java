package org.cypmaster.servlets;

import org.cypmaster.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"}, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

    private LoginService loginService;
    private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    private final static String SESSION_USERNAME = "username";
    private final static String USERNAME_FORM_NAME = "username";
    private final static String PASSWORD_FORM_NAME = "password";
    private final static String REMEMBER_ME_FORM_NAME = "remember_me";
    private final static String REMEMBER_ME_OPTION_SELECTED = "on";
    private final static String HOME_PAGE_LOCATION = "/home";
    private final static String LOGIN_PAGE_LOCATION = "/login.jsp";
    private final static String FAILED_TO_VALIDATE_INPUT_DATA_ATTRIBUTE = "message";
    private final static String LOGIN_ERROR_MESSAGE_ATTRIBUTE_NAME = "errorMessages";

    @Override
    public void init() throws ServletException {
        loginService = LoginService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (loginService.isAuthenticated(req)) {
            resp.sendRedirect(req.getContextPath() + HOME_PAGE_LOCATION);
            return;
        }
        req.getRequestDispatcher(LOGIN_PAGE_LOCATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> username = Optional.ofNullable(req.getParameter(USERNAME_FORM_NAME));
        Optional<String> password = Optional.ofNullable(req.getParameter(PASSWORD_FORM_NAME));
        Optional<String> rememberMe = Optional.ofNullable(req.getParameter(REMEMBER_ME_FORM_NAME));
        boolean rememberMeOption = rememberMe.isPresent() && rememberMe.get().equals(REMEMBER_ME_OPTION_SELECTED);
        Map<String, String> errorMessages = new HashMap<>();

        if (!username.isPresent() || !password.isPresent()) {
            logger.info("Not all fields were completed");
            errorMessages.put(FAILED_TO_VALIDATE_INPUT_DATA_ATTRIBUTE, "Complete all fields");
            req.setAttribute(LOGIN_ERROR_MESSAGE_ATTRIBUTE_NAME, errorMessages);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        boolean goodCredentials = loginService.checkCredentials(req, resp, username.get(), password.get(), rememberMeOption);
        if (!goodCredentials) {
            logger.info("Wrong credentials");
            errorMessages.put(FAILED_TO_VALIDATE_INPUT_DATA_ATTRIBUTE, "Wrong Credentials");
            req.setAttribute(LOGIN_ERROR_MESSAGE_ATTRIBUTE_NAME, errorMessages);
            req.getRequestDispatcher(LOGIN_PAGE_LOCATION).forward(req, resp);
            return;
        }

        logger.info("User:%s was authenticated", username.get());
        HttpSession session = req.getSession(true);
        session.setAttribute(SESSION_USERNAME, username.get());

        resp.sendRedirect(req.getContextPath() + HOME_PAGE_LOCATION);

    }
}
