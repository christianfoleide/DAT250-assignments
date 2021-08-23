package no.hvl.dat250.herokudemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class SimpleController {

    @GetMapping(value = "/hello")
    public ResponseEntity<Hello> sayHello() {
        return new ResponseEntity<>(
                Hello.builder()
                    .message("200 OK")
                    .statusCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
    }
}
