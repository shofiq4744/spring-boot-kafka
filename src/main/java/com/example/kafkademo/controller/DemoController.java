package com.example.kafkademo.controller;

import com.example.kafkademo.kafka.JsonKafkaProducer;
import com.example.kafkademo.kafka.KafkaProducer;
import com.example.kafkademo.payload.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class DemoController {

    private KafkaProducer kafkaProducer;
    private JsonKafkaProducer jsonKafkaProducer;

    @Autowired
    public DemoController(KafkaProducer kafkaProducer, JsonKafkaProducer jsonKafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMsg(@RequestParam String msg){
        kafkaProducer.sendMessage(msg);
         return ResponseEntity.ok("send");
    }

    @PostMapping("/publish/obj")
    public ResponseEntity<String> sendMsgObj(@RequestBody User user){
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("send user");
    }
}
