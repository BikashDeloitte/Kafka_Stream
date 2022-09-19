package training.kafka.kafkaProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import training.kafka.kafkaProject.service.InvoiceGeneratorService;
import training.kafka.kafkaProject.service.KafkaProducerAPIService;

@SpringBootApplication
public class KafkaProjectApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectApplication.class, args);
	}

	@Autowired
	private KafkaProducerAPIService kafkaProducerAPIService;

	@Autowired
	private InvoiceGeneratorService invoiceGenerator;

	/*
	* generating random invoice every second
	* */
	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (int i = 0; i < 60; i++) {
			kafkaProducerAPIService.sendMessage(invoiceGenerator.getNextInvoice());
			Thread.sleep(1000);
		}
	}
}
