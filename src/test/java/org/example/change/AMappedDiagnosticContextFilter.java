//package org.example.change.filter;
//
//import org.slf4j.MDC;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import javax.servlet.*;
//import org.springframework.web.server.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.UUID;
//
///**
// * 实现ip访问次数
// */
//@Component
//@Order(1)
////@WebFilter(urlPatterns = "/*")
//public class AMappedDiagnosticContextFilter implements WebFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String uuid = UUID.randomUUID().toString();
//        System.out.println("过滤器");
//        MDC.put("TRACE_ID", uuid);
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> MDC.remove("TRACE_ID")));
//    }
//}
