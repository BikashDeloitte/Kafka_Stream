package training.kafka.kafkaProject.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import training.kafka.kafkaProject.entity.Invoice;

@Service
@Log4j2
public class KafkaProducerAPIService {

    @Autowired
    KafkaTemplate<String, Invoice> kafkaTemplate;

    public void sendMessage(Invoice invoice) {
        String topic = "users";

        log.info(String.format("Producing Invoice No: %s Customer Type: %s",
                invoice.getInvoiceNumber(),
                invoice.getCustomerType()));
        kafkaTemplate.send(topic, invoice.getStoreID(), invoice);
    }
}
