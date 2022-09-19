package training.kafka.kafkaProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import training.kafka.kafkaProject.entity.IncomingMessage;
import training.kafka.kafkaProject.service.KafkaProducerService;

@RestController
public class controller {
    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/kafka/message")
    public String sendMessageToKafka(@RequestBody IncomingMessage incomingMessage){
        kafkaProducerService.sendMessageToKafka(incomingMessage.getTopic(), incomingMessage.getKey(), incomingMessage.getValue());
        return String.format("Success - Message for key %s is sent to Kafka Topic: %s",
                incomingMessage.getKey(), incomingMessage.getTopic());
    }
}
