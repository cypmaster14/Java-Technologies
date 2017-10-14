package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ResponseWriter {

    void writeResponse(HttpServletResponse resp, Map<String, String> words) throws IOException;
}
