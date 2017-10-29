package org.cypmaster.filters;

import org.cypmaster.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/input", "/result", "/home"})
public class AuthenticationFilter implements Filter {

    private LoginService loginService;
    private final static String SESSION_NAME = "username";


    private final static String LOGIN_PAGE_LOCATION = "/login";
    private final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginService = LoginService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String loginURI = req.getContextPath() + LOGIN_PAGE_LOCATION;
        boolean loginRequest = req.getRequestURI().equals(loginURI);

        if (loginService.isAuthenticated(req) || loginRequest) {
            filterChain.doFilter(req, resp);
        } else {
            logger.info("Authorized request from user:" + req.getSession().getAttribute(SESSION_NAME));
            resp.sendRedirect(LOGIN_PAGE_LOCATION);
        }
    }
}
