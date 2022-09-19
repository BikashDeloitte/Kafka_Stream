package training.kafka.kafkaProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import training.kafka.kafkaProject.entity.LineItem;

import java.io.File;
import java.util.Random;

/*
 * Generating a random items from item.json with random quantity
 * */
@Service
public class ItemGeneratorService {
    private final Random random;
    private final Random qty;
    private final LineItem[] products;

    public ItemGeneratorService() {
        String dataFile = "src/main/resources/data/item.json";
        ObjectMapper mapper = new ObjectMapper();
        random = new Random();
        qty = new Random();
        //reading data from item.json using ObjectMapper readValue method and storing it to LineItem entity array
        try {
            products = mapper.readValue(new File(dataFile), LineItem[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //generating random number to use as index of LineItem entity array
    private int getIndex() {
        return random.nextInt(100);
    }

    //generating random quantity of item
    private int getQuantity() {
        return qty.nextInt(2) + 1;
    }

    //generating random item
    public LineItem getNextProduct() {
        LineItem lineItem = products[getIndex()];

        //setting item quantity and total price
        lineItem.setItemQty(getQuantity());
        lineItem.setTotalValue(lineItem.getItemPrice() * lineItem.getItemQty());
        return lineItem;
    }
}
