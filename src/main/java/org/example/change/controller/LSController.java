package org.example.change.controller;

import org.example.change.entity.LS;
import org.example.change.service.LSService;
import org.example.change.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

@RestController
public class LSController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LSService lsService;
    @GetMapping("/set")
    public String set(String Long) throws MalformedURLException {
        URL url = new URL(Long);
        String host = url.getHost();
        String path = url.getPath();
        LS ls = new LS();
        ls.setDomain(host);
        System.out.println("set");
        String Short;
        int uu = Math.abs(UUID.randomUUID().toString().hashCode());
        BigInteger b = new BigInteger(String.valueOf(uu));
        Short = toBase62(b);
        ls.setLong(Long);
        ls.setUu(Short);
        ls.setShort("http://localhost:8080/"+Short);
        lsService.save1(ls);
        lsService.save2(ls);
        lsService.save3(ls);

        System.out.println("创建成功  "+Long+":"+Short);
        System.out.println(redisTemplate.opsForValue().get(Short));
        redisTemplate.opsForValue().set(Short,Long);

        return "创建成功  "+Short;
    }
    @GetMapping("/get")
    public void get() {
        System.out.println("ggg");
    }
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String toBase62(BigInteger decimalNumber) {
        StringBuilder result = new StringBuilder();
        BigInteger base = BigInteger.valueOf(62);
        while (decimalNumber.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = decimalNumber.divideAndRemainder(base);
            decimalNumber = divmod[0];
            int index = divmod[1].intValue();
            result.insert(0, BASE62.substring(index, index + 1));
        }

        return result.toString();
    }

}
