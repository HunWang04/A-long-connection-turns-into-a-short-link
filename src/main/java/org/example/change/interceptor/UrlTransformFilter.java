package org.example.change.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

@Component
public class UrlTransformFilter implements HandlerInterceptor {
    static ThreadLocal<String> threadLocalRedirection = new ThreadLocal();
    static ThreadLocal<String> threadLocalShort = new ThreadLocal();
    static ThreadLocal<String> threadLocalCode = new ThreadLocal();
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        HttpServletRequest req= (HttpServletRequest)request;
        URL Url = new URL(req.getRequestURL().toString());
        threadLocalShort.set(req.getRequestURL().toString());
        String path = Url.getPath().substring(1);
        threadLocalCode.set(path);
        String url = req.getRequestURL().toString();

        System.out.println(path);
        String s = (String) redisTemplate.opsForValue().get(path);
        if(s!=null) System.out.println("不是null" + "url=" +s);
        else System.out.println("s = "+"null");
        if(s!=null)
            System.out.println(s);
        threadLocalRedirection.set(s);
        return true;
    }
}
