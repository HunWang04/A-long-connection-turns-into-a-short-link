package org.example.change.config;

import org.example.change.interceptor.ExtractRequestHeaderTransformFilter;
import org.example.change.interceptor.RedirectionTransformFilter;
import org.example.change.interceptor.TransformEventProcessTransformFilter;
import org.example.change.interceptor.UrlTransformFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getExtractRequestHeaderTransformFilter())
                .addPathPatterns("/*")
                .excludePathPatterns("/set","/index.html","/add");
        registry.addInterceptor(getUrlTransformFilter())
                .addPathPatterns("/*")
                .excludePathPatterns("/set","/index.html","/add");
        registry.addInterceptor(getRedirectionTransformFilter())
                .addPathPatterns("/*")
                .excludePathPatterns("/set","/index.html");
        registry.addInterceptor(getTransformEventProcessTransformFilter())
                .addPathPatterns("/*")
                .excludePathPatterns("/set","/index.html","/add");

    }
    @Bean
    public ExtractRequestHeaderTransformFilter getExtractRequestHeaderTransformFilter(){
        return new ExtractRequestHeaderTransformFilter();
    }
    @Bean
    public RedirectionTransformFilter getRedirectionTransformFilter(){
        return new RedirectionTransformFilter();
    }
    @Bean
    public UrlTransformFilter getUrlTransformFilter(){
        return new UrlTransformFilter();
    }
    @Bean
    public TransformEventProcessTransformFilter getTransformEventProcessTransformFilter(){
        return new TransformEventProcessTransformFilter();
    }
}
