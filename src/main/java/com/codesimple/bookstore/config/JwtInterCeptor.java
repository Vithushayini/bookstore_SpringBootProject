package com.codesimple.bookstore.config;

import com.codesimple.bookstore.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class JwtInterCeptor extends WebRequestHandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;
    public JwtInterCeptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURI());
        String auth=request.getHeader("authorization");

        if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))){
            jwtUtils.verify(auth);
        }


        return super.preHandle(request, response, handler);
    }
}
