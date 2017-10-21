package org.cypmaster.servlets;

import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.services.UserService;
import org.cypmaster.recaptcha.VerifyRecaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "webServlet", urlPatterns = {"/words"}, loadOnStartup = 1)
public class WordsServlet extends HttpServlet {

    private final static String KEY_FORM_NAME = "key";
    private final static String VALUE_FORM_NAME = "value";
    private final static String CATEGORY_FORM_NAME = "category";
    private final static String COOKIE_REMEMBER_CATEGORY_ID = "categoryId";
    private final static String RECAPTCHA_FORM_NAME = "g-recaptcha-response";
    private final static String ERROR_PAGE_LOCATION = "/WEB-INF/error.jsp";
    private final static String WORDS_PAGE_LOCATION = "/WEB-INF/words.jsp";

    private final static String ERROR_PAGE_MESSAGE_ATTRIBUTE_NAME = "message";
    private final static String USER_INPUTS_ATTRIBUTE_NAME = "infos";
    private final static String ROBOT_DETECTED_MESSAGE = "You are a robot!!!";
    private final static String INVALID_INPUT_MESSAGE = "Complete all the fields";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String gRecaptchaResponse = req.getParameter(RECAPTCHA_FORM_NAME);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if (!verify) {
            getServletContext().log("Robot Detected");
            req.setAttribute(ERROR_PAGE_MESSAGE_ATTRIBUTE_NAME, ROBOT_DETECTED_MESSAGE);
            req.getRequestDispatcher(ERROR_PAGE_LOCATION).forward(req, resp);
            return;
        }

        logInputFromUser(req);
        if (!inputIsValid(req)) {
            getServletContext().log("Invalid output");
            req.setAttribute(ERROR_PAGE_MESSAGE_ATTRIBUTE_NAME, INVALID_INPUT_MESSAGE);
            req.getRequestDispatcher(ERROR_PAGE_LOCATION).forward(req, resp);
            return;
        }

        try {
            addUserInput(req);
        } catch (Exception e) {
            getServletContext().log("Error occurred");
            req.setAttribute(ERROR_PAGE_MESSAGE_ATTRIBUTE_NAME, e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE_LOCATION).forward(req, resp);
            return;
        }


        List<UserInputDTO> userInputs = userService.getInputs();
        getServletContext().log("[INPUTS]" + userInputs.toString());
        req.setAttribute(USER_INPUTS_ATTRIBUTE_NAME, userInputs);
        setRememberCategoryCookie(resp, req.getParameter(CATEGORY_FORM_NAME));
        req.getRequestDispatcher(WORDS_PAGE_LOCATION).forward(req, resp);
    }

    private synchronized void addUserInput(HttpServletRequest req) throws Exception {
        String key = req.getParameter(KEY_FORM_NAME);
        String value = req.getParameter(VALUE_FORM_NAME);
        int category = Integer.valueOf(req.getParameter(CATEGORY_FORM_NAME));
        userService.addUserInput(key, value, category);
        getServletContext().log("Input inserted");

    }

    private void logInputFromUser(HttpServletRequest req) {
        String key = req.getParameter(KEY_FORM_NAME);
        String value = req.getParameter(VALUE_FORM_NAME);
        String categoryId = req.getParameter(CATEGORY_FORM_NAME);
        getServletContext().log(key + " " + value + " " + categoryId);
    }

    private boolean inputIsValid(HttpServletRequest req) {
        Optional<String> key = Optional.ofNullable(req.getParameter(KEY_FORM_NAME));
        Optional<String> value = Optional.ofNullable(req.getParameter(VALUE_FORM_NAME));
        Optional<String> categoryId = Optional.ofNullable(req.getParameter(CATEGORY_FORM_NAME));

        return key.isPresent() && !key.get().isEmpty()
                && value.isPresent() && !value.get().isEmpty()
                && categoryId.isPresent() && !categoryId.get().isEmpty();
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
