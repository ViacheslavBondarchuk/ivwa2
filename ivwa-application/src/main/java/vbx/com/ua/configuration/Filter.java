package vbx.com.ua.configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * author: Viacheslav Bondarchuk
 * date: 5/4/20
 * time: 6:16 PM
 **/
public class Filter implements javax.servlet.Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
