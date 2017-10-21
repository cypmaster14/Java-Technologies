package org.cypmaster.filters;

import org.cypmaster.wrappers.CopyrightHttpServletResponseWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/input", "/result", "/home", "/query1", "/query2"})
public class CopyrightFilter implements Filter {

    private final static String FOOTER_HTML = "<footer class=\"page-footer\">\n" +
            "\t\t\t<div class=\"footer-copyright\">\n" +
            "\t\t\t\t<div class=\"container\">\n" +
            "\t\t\t\t\t© Copyright Infoiasi 2017\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</footer>";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponseWrapper copyrightWrapper = new CopyrightHttpServletResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest, copyrightWrapper);

        String htmlFile = copyrightWrapper.toString();
        Document html = Jsoup.parse(htmlFile);
        Element body = html.select("body").first();
        body.append(FOOTER_HTML);

        //Write the HTML to the stream that will be send to client
        PrintWriter out = servletResponse.getWriter();
        out.write(html.outerHtml());


    }
}
