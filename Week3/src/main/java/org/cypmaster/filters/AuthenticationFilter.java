package org.cypmaster.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/input", "/result", "/home"})
public class AuthenticationFilter implements Filter {

    private final static String USERNAME_SESSION_NAME = "username";
    private final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Optional<HttpSession> session = Optional.of(req.getSession());
        if (!session.isPresent() || session.get().getAttribute(USERNAME_SESSION_NAME) == null) {
            logger.info("An unauthorized request");
            resp.sendRedirect("/login.jsp");
            return;
        }

        logger.info("Authorized request from user:" + session.get().getAttribute(USERNAME_SESSION_NAME));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
