package com.codesimple.bookstore.common;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver pageResolver=new PageableHandlerMethodArgumentResolver();

        pageResolver.setPageParameterName("page-number");
        pageResolver.setSizeParameterName("page-size");
        pageResolver.setOneIndexedParameters(true);

        PageRequest defaultPageable= PageRequest.of(0, 5);

        pageResolver.setFallbackPageable(defaultPageable);

        resolvers.add(pageResolver);
    }
}
