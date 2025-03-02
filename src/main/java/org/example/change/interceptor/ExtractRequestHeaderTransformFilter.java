package org.example.change.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class ExtractRequestHeaderTransformFilter implements HandlerInterceptor {
    static ThreadLocal<Enumeration> threadLocalName =new ThreadLocal<>();
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        System.out.println("EX");
        Enumeration er = request.getHeaderNames();//获取请求头的所有name值
        threadLocalName.set(er);




        System.out.println("1");
        return true;
    }
}
