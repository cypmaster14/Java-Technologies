package org.cypmaster.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by Ciprian at 12/16/2017
 */

public class SearchProjectsInterceptor {

    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception {
        System.out.println("Intercepting SearchBean method:" + ctx.getMethod().getName());
        return ctx.proceed();
    }

}
