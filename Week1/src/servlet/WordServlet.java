package servlet;

import util.BrowserResponseWriter;
import util.DesktopResponseWriter;
import util.ResponseWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "WordServlet", urlPatterns = {"/word"})
public class WordServlet extends HttpServlet {

    private final static String KEY = "key";
    private final static String VALUE = "value";
    private final static String WORD_FILE_NAME = "words.txt";
    private Map<String, String> words;
    private PrintWriter writer;

    @Override
    public void init() throws ServletException {
        words = new ConcurrentHashMap<>();
        String filename = getServletContext().getRealPath(WORD_FILE_NAME);
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        respondToClient(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter(KEY);
        String value = req.getParameter(VALUE);
        words.put(key, value);
        synchronized (this) {
            writer.write(key + " " + value + "\n");
        }
        getServletContext().log(key + " " + value);
        logInfo(req);
        respondToClient(req, resp);
    }


    @Override
    public void destroy() {
        writer.close();
    }

    private void logInfo(HttpServletRequest req) {
        getServletContext().log("Method:" + req.getMethod());
        getServletContext().log("IP:" + req.getRemoteAddr());
        getServletContext().log("User-Agent:" + req.getHeader("User-Agent"));
        getServletContext().log("Language:" + req.getHeader("Accept-Language"));
        getServletContext().log("Accept:" + req.getHeader("Accept"));
        getServletContext().log("Words:" + words.size());
    }

    private void respondToClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, String> orderedWords = new TreeMap<>(words);
        ResponseWriter responseWriter;
        if (req.getHeader("Accept").contains("text/html")) { //browser request
            responseWriter = new BrowserResponseWriter();
        } else {
            responseWriter = new DesktopResponseWriter();
        }
        responseWriter.writeResponse(resp, orderedWords);

    }
}
