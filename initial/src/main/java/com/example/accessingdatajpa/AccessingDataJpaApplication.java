package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CustomerRepository repo) {
		return args -> {

			log.info("Saving two Customer entities");
			repo.save(new Customer("Christian", "Foleide"));
			repo.save(new Customer("Test", "Testesen"));
			repo.save(new Customer("Test", "Testaren"));
			repo.save(new Customer("Christian", "Testesen"));

			log.info("Fetching customer with ID 1");
			Customer customer = repo.findById(1L).get();
			log.info("Customer={}", customer);

			log.info("Finding all customers");
			List<Customer> allCustomers = StreamSupport
					.stream(repo.findAll().spliterator(), false)
					.collect(Collectors.toList());
			allCustomers.forEach(c -> log.info("customer={}", c));

			log.info("Finding all customers with firstName=Test");

			repo.findByFirstName("Test").forEach(c -> log.info("customer={}", c));
		};
	}
}
