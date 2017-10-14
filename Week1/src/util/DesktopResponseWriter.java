package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class DesktopResponseWriter implements ResponseWriter {

    private final static String PLAIN_TEXT_TYPE = "text/plain";

    @Override
    public void writeResponse(HttpServletResponse resp, Map<String, String> words) throws IOException {
        resp.setContentType(PLAIN_TEXT_TYPE);

        PrintWriter out = resp.getWriter();
        words.forEach((key, value) -> out.printf("%s : %s\n", key, value));

        out.close();
    }
}
