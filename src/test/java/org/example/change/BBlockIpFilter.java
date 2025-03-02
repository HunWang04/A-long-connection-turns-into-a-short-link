//package org.example.change.filter;
//
//
//import org.slf4j.MDC;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import org.springframework.web.server.WebFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.UUID;
//
///**
// * 实现ip访问次数
// */
//@Component
//@Order(2)
////@WebFilter(urlPatterns = "/*")
//public class BBlockIpFilter implements WebFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        String ip = headers.getFirst("x-forwarded-for");
//        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
//            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
//            if (ip.indexOf(",") != -1) {
//                ip = ip.split(",")[0];
//            }
//        }
//        // 获取 IP 地址
//
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> MDC.remove("TRACE_ID")));
//    }
//}
