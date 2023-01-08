package pl.wat.portal_ogloszeniowy_kafka.queues.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.wat.portal_ogloszeniowy_kafka.dtos.OfferShipping;
import pl.wat.portal_ogloszeniowy_kafka.queues.producers.KafkaProducer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final CountDownLatch latch = new CountDownLatch(1);
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaConsumer(KafkaProducer kafkaProducer, ObjectMapper objectMapper) {
        this.kafkaProducer = kafkaProducer;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "purchasedThings")
    public void receive(ConsumerRecord<?, ?> consumerRecord) throws InterruptedException, IOException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        latch.countDown();

        String s = consumerRecord.value().toString();
        OfferShipping offerShipping = objectMapper.readValue(s, OfferShipping.class);
        System.out.println(offerShipping.getTitle());
        TimeUnit.SECONDS.sleep(5);
        offerShipping.setStatus(1);
        kafkaProducer.send("shippingStatus", objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(offerShipping));
        TimeUnit.SECONDS.sleep(5);
        offerShipping.setStatus(2);
        kafkaProducer.send("shippingStatus", objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(offerShipping));
    }
}
