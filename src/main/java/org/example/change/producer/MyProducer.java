package org.example.change.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.change.entity.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Slf4j
@Component()
public class MyProducer {
    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;
    public static final String topic = "RLT_TEST_TOPIC";
    @Autowired
    private RocketMQTemplate rocketMQTemplate;



    // 直接注入使用，用于发送消息到broker服务器

    /**
     * 普通发送（这里的参数对象User可以随意定义，可以发送个对象，也可以是字符串等）
     */

    public void sendMsg(Msg msg) {
        rocketMQTemplate.convertAndSend(topic + ":tag1", msg);
//        rocketMQTemplate.send(topic + ":tag1", MessageBuilder.withPayload(user).build()); // 等价于上面一行
    }

}
