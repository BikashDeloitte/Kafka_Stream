package training.kafka.kafkaProject.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

//interface for input and output channels
public interface KafkaListenerBinding {

    /*
    * change the spring cloud to Hoxton.SR12 version so that we can use @Input
    *
    * @Input indicate the input-channel-1 is a input channel
    * */
    @Input("input-channel-1")
    KStream<String,String> inputStream();
}
