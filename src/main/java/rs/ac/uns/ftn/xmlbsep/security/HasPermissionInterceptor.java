package rs.ac.uns.ftn.xmlbsep.security;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.exception.UnauthorizedAccessException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Interceptor
@HasPermission
public class HasPermissionInterceptor {

    @Context
    private HttpServletRequest request;

    public HasPermissionInterceptor() {
        super();
    }

    @AroundInvoke
//    @Authenticate
    public Object intercept(InvocationContext context) throws Exception {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            throw new UnauthorizedAccessException("Not logged in");
        }
        HasPermission permission = context.getMethod().getAnnotation(HasPermission.class);
        String neededPermission = permission.value();
        if (!user.hasPermission(neededPermission)) {
            throw new IllegalStateException("Do not have permision " + neededPermission);
        }
        System.out.println("HasPermissionInterceptor.intercept needs readInvoice perm");

        return context.proceed();
    }

}
