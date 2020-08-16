package com.example.configs;

import com.example.persistence.model.Customer;
import com.example.persistence.model.Order;
import com.example.persistence.model.Status;
import com.example.persistence.repo.CustomerRepository;
import com.example.persistence.repo.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, OrderRepository orderRepository) {
        return args -> {
            customerRepository.save(new Customer("Ryan", "Gosling", "10000001111"));
            customerRepository.save(new Customer("Matthew", "McConaughey", "20002132543"));

            customerRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

            orderRepository.save(new Order("Macbook", Status.COMPLETED, 1L));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS, 2L));

            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}
