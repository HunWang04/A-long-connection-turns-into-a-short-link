package org.example.change.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
public class Bfilter implements Filter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private BloomFilter<String> bloomFilter;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取当前请求的URI（Uniform Resource Identifier）
        String uri = request.getRequestURI();
        // 获取Redis的操作对象，用于操作List类型数据
        ListOperations<String,String> listOperations=redisTemplate.opsForList();
        // 从Redis的List类型数据中获取名为"BU"的数据
        List<String> bb = listOperations.range("BU", 0, -1);
        // 将List中的每个元素加入到BloomFilter中
        for (int i = 0; i < bb.size(); i++) {
            bloomFilter.put(bb.get(i));
        }
        // 检查当前URI是否可能存在于BloomFilter中
        boolean contains = bloomFilter.mightContain(uri);
        // 如果BloomFilter认为当前URI存在，则拒绝访问
        if(contains){
            servletResponse.getWriter().write(JSON.toJSONString("NOT ALLOWED"));
            return;
        }
//        // 再次检查Redis中该URI的访问计数值是否为1
        System.out.println(redisTemplate.opsForValue().get(uri));
        Object value = redisTemplate.opsForValue().get(uri);
        int s = (value == null) ? 0 : (int) value;
        if(s == 1){
            servletResponse.getWriter().write(JSON.toJSONString("NOT ALLOWED"));
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }
}
