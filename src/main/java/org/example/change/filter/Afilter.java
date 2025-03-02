package org.example.change.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@WebFilter(
        urlPatterns = "/*",
        dispatcherTypes = DispatcherType.REQUEST // 仅拦截原始请求
)
@Component

public class Afilter implements Filter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private BloomFilter<String> bloomFilter;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取Redis的操作对象，用于操作List类型数据
        ListOperations<String,String> listOperations=redisTemplate.opsForList();
        // 将传入的ServletRequest强制转换为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取客户端IP地址
        String ip = request.getRemoteAddr();
        // 从Redis的List类型数据中获取名为"BB"的数据
        List<String> bb = listOperations.range("BB", 0, -1);
        // 从Redis中获取当前IP对应的访问计数值
        int s1 = (int)redisTemplate.opsForValue().get(ip);
        // 将List中的每个元素加入到BloomFilter中
        for (int i = 0; i < bb.size(); i++) {
            bloomFilter.put(bb.get(i));
        }
        // 检查当前IP是否可能存在于BloomFilter中
        boolean contains = bloomFilter.mightContain(ip);
        // 如果BloomFilter认为当前IP存在，则拒绝访问
        if(contains){
            servletResponse.getWriter().write(JSON.toJSONString("NOT ALLOWED"));
            return;
        }
        // 再次检查Redis中该IP的访问计数值是否为1
        Object value = redisTemplate.opsForValue().get(ip);
        int s = (value == null) ? 0 : (int) value;
        if(s == 1){
            servletResponse.getWriter().write(JSON.toJSONString("NOT ALLOWED"));
            return;
        }
        System.out.println("AFilter1");
        filterChain.doFilter(request, servletResponse);
    }
}
