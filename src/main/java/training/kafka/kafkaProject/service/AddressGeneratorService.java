package training.kafka.kafkaProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import training.kafka.kafkaProject.entity.DeliveryAddress;

import java.io.File;
import java.util.Random;

/*
* Generating a random address from address.json
* */
@Service
public class AddressGeneratorService {
    private final Random random;
    private final DeliveryAddress[] addresses;

    public AddressGeneratorService() {
        final String dataFile = "src/main/resources/data/address.json";
        final ObjectMapper mapper;
        random = new Random();
        mapper = new ObjectMapper();

        //reading data from address.json using ObjectMapper readValue method and storing it to DeliveryAddress entity array
        try {
            addresses = mapper.readValue(new File(dataFile), DeliveryAddress[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //generating random number to use as index of DeliveryAddress entity array
    private int getIndex() {
        return random.nextInt(100);
    }

    //return a random address
    public DeliveryAddress getNextAddress() {
        return addresses[getIndex()];
    }
}
