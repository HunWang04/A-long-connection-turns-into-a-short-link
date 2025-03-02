package org.example.change.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.example.change.entity.Msg;
import org.example.change.producer.MyProducer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Enumeration;

import static org.example.change.interceptor.ExtractRequestHeaderTransformFilter.threadLocalName;
import static org.example.change.interceptor.RedirectionTransformFilter.threadLocalRedictedJudge;
import static org.example.change.interceptor.RedirectionTransformFilter.threadLocalRedictedLong;
import static org.example.change.interceptor.UrlTransformFilter.threadLocalCode;
import static org.example.change.interceptor.UrlTransformFilter.threadLocalShort;

public class TransformEventProcessTransformFilter implements HandlerInterceptor {
    @Autowired
    private MyProducer mqProducerService;
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        System.out.println("开始");
        Enumeration er = threadLocalName.get();
        Msg msg = new Msg();
        //msg.setEnumeration(threadLocalName.get());
//        msg.setRequest(request);
        //1
        String name = (String) er.nextElement();
        String value = request.getHeader(name);
        System.out.println(name + "=" + value);
        msg.setClient(value);


        java.util.Date date = new java.util.Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        msg.setDate(new Timestamp(System.currentTimeMillis()));
        msg.setStatus(threadLocalRedictedJudge.get());
        msg.setLong(threadLocalRedictedLong.get());
        msg.setShort(threadLocalShort.get());
        msg.setCode(threadLocalCode.get());
        if(threadLocalRedictedJudge.get()==1){
            msg.setEffective(1);
        }else{
            msg.setIneffective(0);
        }
        mqProducerService.sendMsg(msg);
        System.out.println("完毕");
        return false;
    }
}
