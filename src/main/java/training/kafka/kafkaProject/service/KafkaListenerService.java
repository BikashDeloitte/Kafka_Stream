package training.kafka.kafkaProject.service;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import training.kafka.kafkaProject.bindings.KafkaListenerBinding;

@Service
@Log4j2
/*
* to tell spring we want to communicate with messaging system using channels according in this class
* @EnableBinding - enable the read of messages
* */
@EnableBinding(KafkaListenerBinding.class)
public class KafkaListenerService {

    //listen to which channel
    @StreamListener("input-channel-1")
    public void process(KStream<String, String> input) {
        input.foreach((k,v) -> log.info(String.format("Key: %s, Value: %s",k,v)));
    }
}
