package application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SilviuG on 20.11.2017.
 */
@WebServlet(name = "AppServlet", urlPatterns = {"/"})
public class AppServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("operation") == null || request.getParameter("operation").isEmpty()) {
            response.getWriter().print("Reponse!!");
        } else {
            request.getRequestDispatcher("/" + request.getParameter("operation")).forward(request, response);
        }
    }
}
