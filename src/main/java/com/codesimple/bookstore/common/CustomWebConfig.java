package com.codesimple.bookstore.common;

import com.codesimple.bookstore.config.JwtInterCeptor;
import com.codesimple.bookstore.dto.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterCeptor jwtInterCeptor;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        //sort
        SortHandlerMethodArgumentResolver sortResolver=new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("order-by");

        PageableHandlerMethodArgumentResolver pageResolver=new PageableHandlerMethodArgumentResolver(sortResolver);

        pageResolver.setPageParameterName("page-number");
        pageResolver.setSizeParameterName("page-size");
        pageResolver.setOneIndexedParameters(true);

        PageRequest defaultPageable= PageRequest.of(0, 5);

        pageResolver.setFallbackPageable(defaultPageable);

        resolvers.add(pageResolver);
    }

    public CustomWebConfig(JwtInterCeptor jwtInterCeptor){
        this.jwtInterCeptor=jwtInterCeptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterCeptor);
    }

    @Bean
    @RequestScope
    public RequestMeta getRequestMeta(){
        return new RequestMeta();
    }

//    @Bean
//    public JwtInterCeptor jwtInterCeptor(){
//        return new JwtInterCeptor(getRequestMeta());
//    }
}
