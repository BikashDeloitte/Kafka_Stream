package training.kafka.kafkaProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.kafka.kafkaProject.entity.DeliveryAddress;
import training.kafka.kafkaProject.entity.Invoice;
import training.kafka.kafkaProject.entity.LineItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Log4j2
public class InvoiceGeneratorService {
    private final Random invoiceIndex;
    private final Random invoiceNumber;
    private final Random numberOfItems;
    private final Invoice[] invoices;

    @Autowired
    AddressGeneratorService addressGeneratorService;
    @Autowired
    ItemGeneratorService itemGeneratorService;

    public InvoiceGeneratorService() {
        String dataFile = "src/main/resources/data/invoice.json";
        invoiceIndex = new Random();
        invoiceNumber = new Random();
        numberOfItems = new Random();
        ObjectMapper mapper;
        mapper = new ObjectMapper();
        try {
            invoices = mapper.readValue(new File(dataFile), Invoice[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndex() {
        return invoiceIndex.nextInt(100);
    }

    private int getNewInvoiceNumber() {
        return invoiceNumber.nextInt(99999999) + 99999;
    }

    private int getNoOfItems() {
        return numberOfItems.nextInt(4) + 1;
    }

    public Invoice getNextInvoice() {
        Invoice invoice = invoices[getIndex()];
        invoice.setInvoiceNumber(Integer.toString(getNewInvoiceNumber()));
        invoice.setCreatedTime(System.currentTimeMillis());

        //if HOME-DELIVERY then add address
        if ("HOME-DELIVERY".equalsIgnoreCase(invoice.getDeliveryType())) {
            DeliveryAddress deliveryAddress = addressGeneratorService.getNextAddress();
            invoice.setDeliveryAddress(deliveryAddress);
        }
        int itemCount = getNoOfItems();
        double totalAmount = 0.0;
        List<LineItem> items = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            LineItem item = itemGeneratorService.getNextProduct();
            totalAmount = totalAmount + item.getTotalValue();
            items.add(item);
        }
        invoice.setNumberOfItems(itemCount);
        invoice.setInvoiceLineItems(items);
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxableAmount(totalAmount);
        invoice.setCGST(totalAmount * 0.025);
        invoice.setSGST(totalAmount * 0.025);
        invoice.setCESS(totalAmount * 0.00125);
        log.debug(invoice);
        return invoice;
    }
}
