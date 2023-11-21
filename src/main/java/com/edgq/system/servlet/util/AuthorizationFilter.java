package com.edgq.system.servlet.util;

import com.edgq.shopsystem.controller.SessionBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edwin
 */
@WebFilter(urlPatterns = "*.xhtml")
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Inject
    private SessionBean session;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession ses = request.getSession(false);
            
            System.out.println("Inicio-----------");
            System.out.println(!session.isLogged());
            System.out.println(!request.getRequestURI().endsWith("/login.xhtml"));
            System.out.println(!request.getRequestURI().contains("/javax.faces.resources/"));
            System.out.println("Fin-----------"); 
            
            if (!session.isLogged() && !request.getRequestURI().endsWith("/login.xhtml")
                    && !request.getRequestURI().contains("/javax.faces.resources/")) {
                System.out.println("Filter...");
                response.sendRedirect(request.getContextPath() + "/login.xhtml");
            } else {
                chain.doFilter(req, res);
                System.out.println("Normal...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
