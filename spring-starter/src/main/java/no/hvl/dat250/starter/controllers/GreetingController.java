package no.hvl.dat250.starter.controllers;

import no.hvl.dat250.starter.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return Greeting.builder()
                .id(counter.incrementAndGet())
                .content(String.format(TEMPLATE, name))
                .build();
    }
}
