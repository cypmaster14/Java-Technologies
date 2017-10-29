package org.cypmaster.services;

import org.cypmaster.entities.Person;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

public class LoginService {

    private static LoginService instance;

    private PersonService personService;

    private static final String REMEMBER_ME_COOKIE_NAME = "remember_me";
    private final static String SESSION_USERNAME = "username";


    private LoginService() {
        personService = PersonService.getInstance();
    }

    public static synchronized LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }


    public boolean isAuthenticated(HttpServletRequest request) {
        Optional<HttpSession> sessionOptional = Optional.ofNullable(request.getSession(false));
        if (!sessionOptional.isPresent()) {
            return loginRememberedUser(request);
        }
        return sessionOptional.get().getAttribute(SESSION_USERNAME) != null || loginRememberedUser(request);

    }

    public boolean checkCredentials(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean rememberMe) {

        Optional<Person> user = personService.doLogin(username, password);
        if (!user.isPresent()) {
            return false;
        }

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USERNAME, username);
        if (rememberMe) {
            rememberUser(username, response);
        }
        return true;


    }

    private Optional<Cookie> getRememberMeCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        return Arrays
                .stream(cookies)
                .filter(cookie -> cookie.getName().equals(REMEMBER_ME_COOKIE_NAME))
                .findAny();
    }

    private boolean loginRememberedUser(HttpServletRequest request) {
        Optional<Cookie> rememberMeCookie = getRememberMeCookie(request);
        if (rememberMeCookie.isPresent()) {
            String id = rememberMeCookie.get().getValue();
            String username = personService.getRememberedUser(id);
            if (username != null) {
                HttpSession session = request.getSession();
                session.setAttribute(SESSION_USERNAME, id);
                return true;
            }
        }
        return false;
    }

    private void rememberUser(String username, HttpServletResponse response) {
        String id = personService.rememberUser(username);
        Cookie cookie = new Cookie(REMEMBER_ME_COOKIE_NAME, id);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
    }


}
