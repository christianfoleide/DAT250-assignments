package no.hvl.dat250.herokudemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class HerokuDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HerokuDemoApplication.class, args);
    }

}
