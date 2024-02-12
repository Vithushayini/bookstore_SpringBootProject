//package com.codesimple.bookstore.config;
//
//import com.codesimple.bookstore.dto.RequestMeta;
//import com.codesimple.bookstore.util.JwtUtils;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.WebRequestInterceptor;
//import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
//
//@Component
//public class JwtInterCeptor extends WebRequestHandlerInterceptorAdapter {
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    private RequestMeta requestMeta;
//    public JwtInterCeptor(WebRequestInterceptor requestInterceptor) {
//        super(requestInterceptor);
//    }
//
//    public JwtInterCeptor(RequestMeta requestMeta) {
//
//        this.requestMeta=requestMeta;
//
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println(request.getRequestURI());
//        String auth=request.getHeader("authorization");
//
//        if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))){
//            Claims claims =jwtUtils.verify(auth);
//
//            requestMeta.setUserName(claims.get("name").toString());
//            requestMeta.setUserId(Long.valueOf(claims.getIssuer()));
//            requestMeta.setUserType(claims.get("type").toString());
//            requestMeta.setEmailId(claims.get("emailId").toString());
//        }
//
//
//        return super.preHandle(request, response, handler);
//    }
//}


package com.codesimple.bookstore.config;

import com.codesimple.bookstore.dto.RequestMeta;
import com.codesimple.bookstore.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterCeptor implements HandlerInterceptor {

   @Autowired
    private JwtUtils jwtUtils;

   @Autowired
    private RequestMeta requestMeta;

    //@Autowired

    public JwtInterCeptor(JwtUtils jwtUtils,RequestMeta requestMeta) {
        this.jwtUtils = jwtUtils;
        this.requestMeta=requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        String auth = request.getHeader("Authorization");

        if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))) {
            Claims claims = jwtUtils.verify(auth);

            requestMeta.setUserName(claims.get("name").toString());
            requestMeta.setUserId(Long.valueOf(claims.getIssuer()));
            requestMeta.setUserType(claims.get("type").toString());
            requestMeta.setEmailId(claims.get("emailId").toString());
        }

        return true;
    }
}
