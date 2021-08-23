package no.hvl.dat250.herokudemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SimpleController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SimpleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<Hello> sayHello() {
        return new ResponseEntity<>(
                Hello.builder()
                    .message("200 OK")
                    .statusCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void doOnApplicationStarted() {
        var configVar = System.getenv("helloMessage");
        if (configVar != null) {
            log.info("Found configVar: {}", configVar);
        }
    }

    @PostConstruct
    public void checkJdbcURL() {
        log.info("DataSource: {}", jdbcTemplate.getDataSource());
    }
}
