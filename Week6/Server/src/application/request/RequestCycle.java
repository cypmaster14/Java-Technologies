package application.request;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SilviuG on 20.11.2017.
 */
public class RequestCycle {
    private static ThreadLocal<HttpServletRequest> servletRequestThreadLocal;

    static {
        servletRequestThreadLocal = new ThreadLocal<>();
    }

    public static void setServletRequest(HttpServletRequest httpServletRequest) {
        servletRequestThreadLocal.set(httpServletRequest);
    }

    public static HttpServletRequest getServletRequest() {
        return servletRequestThreadLocal.get();
    }
}
