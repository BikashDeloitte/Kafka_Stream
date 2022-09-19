package training.kafka.kafkaProject.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaProducerService {

    /*
    * KafkaTemplate wraps a producer and provides convenience methods to send data to kafka topics.
    * Both asynchronous and synchronous methods are provided
    * */
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessageToKafka(String topic, String key, String value) {
        kafkaTemplate.send(topic,key,value);
    }
}
