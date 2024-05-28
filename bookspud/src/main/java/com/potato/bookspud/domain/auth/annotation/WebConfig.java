package com.potato.bookspud.domain.auth.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AccessTokenUserArgumentResolver accessTokenUserArgumentResolver;

    public WebConfig(@Lazy AccessTokenUserArgumentResolver accessTokenUserArgumentResolver) {
        this.accessTokenUserArgumentResolver = accessTokenUserArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(accessTokenUserArgumentResolver);
    }
}
