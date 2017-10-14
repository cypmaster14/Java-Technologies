package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class BrowserResponseWriter implements ResponseWriter {

    private final static String HTTP_CONTENT_TYPE = "text/html";

    @Override
    public void writeResponse(HttpServletResponse resp, Map<String, String> words) throws IOException {
        resp.setContentType(HTTP_CONTENT_TYPE);

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Hello</title></head>");
        out.println("<body>");
        words.forEach((k, v) -> out.printf("<p>%s %s </p>", k, v));

        out.println("</body></html>");
        out.close();
    }
}
