package org.cypmaster.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

/**
 * Created by Ciprian at 12/16/2017
 */

public class SearchProjectsInterceptor {

    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception {
        if (ctx.getMethod().getName().startsWith("find")) {
            System.out.println("Intercepting SearchBean method:" + ctx.getMethod().getName());
            System.out.println(Arrays.toString(ctx.getMethod().getParameters()));
        }
        return ctx.proceed();
    }

}
