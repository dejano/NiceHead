package rs.ac.uns.ftn.xmlbsep.validation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidXMLSchemaInterceptor {

    @AroundInvoke
    public Object logEvent(InvocationContext ctx) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        System.out.println("ValidXMLSchemaInterceptor.logEvent");
        try {
            System.out.println("["+sdf.format(new Date()) + "] Class name: " + ctx.getTarget().getClass() + ", method name: " + ctx.getMethod() + " started.");
            return ctx.proceed();
        } catch (Exception e) {
            System.out.print("["+sdf.format(new Date()) + "] Class name: " + ctx.getTarget().getClass() + ", method name: " + ctx.getMethod() + "thrown an exception.");
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("["+sdf.format(new Date()) + "] Class name: " + ctx.getTarget().getClass() + ", method name: " + ctx.getMethod() + " finished.");
        }
    }
}
