package org.cypmaster.servlets;

import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.services.UserService;
import org.cypmaster.utils.VerifyRecaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "webServlet", urlPatterns = {"/words"})
public class WordsServlet extends HttpServlet {

    private final static String KEY_FORM_NAME = "key";
    private final static String VALUE_FORM_NAME = "value";
    private final static String CATEGORY_FORM_NAME = "category";
    private final static String COOKIE_REMEMBER_CATEGORY_ID = "categoryId";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        try {
            addUserInput(req);
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
            return;
        }

        if (!verify) {
            resp.sendRedirect("http://localhost:8080/input");
            return;
        }


        List<UserInputDTO> userInputs = userService.getInputs();
        getServletContext().log("[INPUTS]" + userInputs.toString());
        req.setAttribute("infos", userInputs);
        setRememberCategoryCookie(resp, req.getParameter(CATEGORY_FORM_NAME));
        req.getRequestDispatcher("/WEB-INF/words.jsp").forward(req, resp);
    }

    private synchronized void addUserInput(HttpServletRequest req) throws Exception {
        String key = req.getParameter(KEY_FORM_NAME);
        String value = req.getParameter(VALUE_FORM_NAME);
        int category = Integer.valueOf(req.getParameter(CATEGORY_FORM_NAME));
        getServletContext().log(key + "' " + value + " " + category);

        userService.addUserInput(key, value, category);
        getServletContext().log("Input inserted");

    }


    private void setRememberCategoryCookie(HttpServletResponse resp, String categoryId) {
        Cookie rememberCategoryCookie = new Cookie(COOKIE_REMEMBER_CATEGORY_ID, categoryId);
        rememberCategoryCookie.setMaxAge(30 * 60);
        resp.addCookie(rememberCategoryCookie);
    }

    @Override
    public void destroy() {

    }
}
