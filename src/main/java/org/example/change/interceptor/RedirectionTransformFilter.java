package org.example.change.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static org.example.change.interceptor.ExtractRequestHeaderTransformFilter.threadLocalName;

@Component
public class RedirectionTransformFilter implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    static ThreadLocal<Integer> threadLocalRedictedJudge =new ThreadLocal<>();
    static ThreadLocal<String> threadLocalRedictedLong =new ThreadLocal<>();
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,final Object handler) throws IOException {
        System.out.println("跳转");
        threadLocalRedictedJudge.set(1);
        String path = UrlTransformFilter.threadLocalRedirection.get();
        threadLocalRedictedLong.set(path);
        Enumeration er = threadLocalName.get();

        if (path == null) {
            response.sendRedirect("/index.html");
            threadLocalRedictedJudge.set(2);
            threadLocalRedictedLong.set("index.html");
            return true;
        }

        response.sendRedirect(path);
        return true;
    }
}
