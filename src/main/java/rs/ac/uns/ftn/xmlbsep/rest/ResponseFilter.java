package rs.ac.uns.ftn.xmlbsep.rest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/ResponseFilter")
public class ResponseFilter implements Filter {
    public void init(FilterConfig config)
            throws ServletException {

    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("Filter user session:" + ((HttpServletRequest) request).getSession().getAttribute("user"));
        chain.doFilter(request, res);
    }

    public void destroy() {
      /* Called before the Filter instance is removed
      from service by the web container*/
    }
}
