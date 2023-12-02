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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author edwin
 * - Esta clase sirve para filtrar las rutas, para que protegerlas y forzar al usuario a iniciar sesión,
 *   que deba ingresar su usuario y contraseña.
 */
@WebFilter(urlPatterns = {"*.xhtml","*.css","*.js","*.svg"})
public class AuthorizationFilter implements Filter {

    @Getter
    @Setter
    private String path;
    
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
            path = request.getRequestURI();
            System.out.println(path);
            
            if (!session.isLogged() && !request.getRequestURI().endsWith("/login.xhtml")
                    && !request.getRequestURI().contains("/javax.faces.resources/")) {
                response.sendRedirect(request.getContextPath() + "/login.xhtml");
            } else {
                chain.doFilter(req, res);
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
