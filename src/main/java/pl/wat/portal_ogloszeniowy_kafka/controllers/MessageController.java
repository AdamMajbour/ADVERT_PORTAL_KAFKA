package pl.wat.portal_ogloszeniowy_kafka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.portal_ogloszeniowy_kafka.queues.producers.KafkaProducer;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final KafkaProducer kafkaProducer;

    @PutMapping("{newMessage}")
    public void putNewMessage(@PathVariable String newMessage){
        kafkaProducer.send("baeldung", newMessage);
    }
}
