package no.hvl.dat250.herokudemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class HerokuDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HerokuDemoApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void doOnStartup() {
        var configVar = System.getenv("helloMessage");
        if (configVar != null) {
            log.info("Found configVar: {}", configVar);
        }
    }
}
