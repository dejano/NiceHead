package rs.ac.uns.ftn.xmlbsep.rest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/LogFilter")
public class ResponseFilter implements Filter {
    public void init(FilterConfig config)
            throws ServletException {

    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws java.io.IOException, ServletException {
        System.out.println("LogFilter.doFilter");
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        chain.doFilter(request, res);
    }

    public void destroy() {
      /* Called before the Filter instance is removed
      from service by the web container*/
    }
}
