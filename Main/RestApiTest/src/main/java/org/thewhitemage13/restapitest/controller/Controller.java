package org.thewhitemage13.restapitest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thewhitemage13.restapitest.entity.Cat;
import org.thewhitemage13.restapitest.kafka.KafkaProducer;
import org.thewhitemage13.restapitest.repository.CatRepository;

@RestController
public class Controller {
    private final KafkaProducer kafkaProducer;
    private final CatRepository catRepository;

    public Controller(KafkaProducer kafkaProducer, CatRepository catRepository) {
        this.kafkaProducer = kafkaProducer;
        this.catRepository = catRepository;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        var cat = catRepository.findById(id);

        kafkaProducer.sendMessage(cat.toString());

        return "Successfully";

    }
}
