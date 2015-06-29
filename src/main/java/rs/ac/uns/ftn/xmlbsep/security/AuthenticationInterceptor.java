package rs.ac.uns.ftn.xmlbsep.security;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.exception.UnauthorizedAccessException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Interceptor
@Authenticate
public class AuthenticationInterceptor {

    @Context
    private HttpServletRequest request;

    public AuthenticationInterceptor() {
        super();
    }

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User from session:" + user);
        if (user == null) {
            throw new UnauthorizedAccessException("Not logged in");
        }
        System.out.println("AuthenticationInterceptor.intercept");
        return context.proceed();
    }
}

