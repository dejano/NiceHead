package rs.ac.uns.ftn.xmlbsep.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Field;

/**
 * Created by dejan on 25.5.2015..
 */
public class HTTPConnectionInterceptor {

    @AroundInvoke
    public Object closeConnection(InvocationContext ctx) throws Exception {
        if (ctx.getMethod() == null)
            return ctx.proceed();

        System.out.println(ctx.getClass().getName());

//        connection.set(ctx, (HttpURLConnection) url.openConnection());

        String methodName = ctx.getMethod().getName();
        if (methodName.startsWith("insert"))
            System.out.println("Method called: " + methodName);
        try
        {
            return ctx.proceed();
        }
        finally
        {
            System.out.println("*** DefaultInterceptor exiting");
        }
    }
}
