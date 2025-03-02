package org.example.change.controller;

import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FilterCOntroller {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private BloomFilter<String> bloomFilter;
    @GetMapping("/add")
    public String addToBloomFilter(String value) {
        redisTemplate.opsForValue().set(value,1);
//        ArrayList<String> userList = new ArrayList<>();
//        userList.add("0:0:0:0:0:0:0:1");
//        listOps.leftPushAll("ip",userList);
//        List<String> lists = listOps.range("users", 0, -1);
//        List<String> users= objectMapper.convertValue(lists, new TypeReference<List<String>>() { });
        ListOperations<String,String> listOperations=redisTemplate.opsForList();
        listOperations.leftPush("BB","0:0:0:0:0:0:0:1");
        List<String> bb = listOperations.range("BB", 0, -1);
        for (int i = 0; i < bb.size(); i++) {
            System.out.println(bb.get(i));
        }
        return "Added to Bloom Filter: " + value;

    }
    @GetMapping("/adda")
    public String adadToBloomFilter(String value) {
        redisTemplate.opsForValue().set(value,1);
        ListOperations<String,String> listOperations=redisTemplate.opsForList();
        listOperations.leftPush("BU",value);
        List<String> bb = listOperations.range("BU", 0, -1);
        return "Added to Bloom Filter: " + value;

    }

}