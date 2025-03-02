package org.example.change.consumer;

import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.example.change.entity.Msg;
import org.example.change.service.LSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class ConsumerService {
    @Autowired
    private LSService lsService;
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", selectorExpression = "tag1", consumerGroup = "Con_Group_One")
    public class ConsumerSend implements RocketMQListener<Msg> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(Msg msg) {

            if(msg.getIneffective()==1){
                lsService.save42(msg);
                System.out.println("接收到无效跳转");
            }
            else {
                lsService.save41(msg);
                System.out.println("接收到有效跳转");
            }
            lsService.save5(msg);
        }
    }
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", consumerGroup = "Con_Group_Two")
    public class ConsumerSend2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String str) {
            log.info("监听到消息：str={}", str);
        }
    }
}