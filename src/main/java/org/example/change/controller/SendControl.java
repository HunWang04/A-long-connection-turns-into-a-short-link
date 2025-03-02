package org.example.change.controller;


import org.example.change.entity.Msg;
import org.example.change.producer.MyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class SendControl {

    @Autowired
    private MyProducer mqProducerService;

    @GetMapping("/send")
    public void send() {
        Msg msg = new Msg();
        mqProducerService.sendMsg(msg);
    }



}
