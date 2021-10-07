package no.hvl.dat250.starter.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import no.hvl.dat250.starter.model.Customer;
import no.hvl.dat250.starter.repositories.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers() {
        var customers = customerRepository.findAll();
        var list = StreamSupport.stream(customers.spliterator(), false).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
