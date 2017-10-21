package org.cypmaster.wrappers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CopyrightHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private final CharArrayWriter out;

    public CopyrightHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        out = new CharArrayWriter();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        //Hide the original Writer
        return new PrintWriter(out);
    }


    @Override
    public String toString() {
        return out.toString();
    }
}
