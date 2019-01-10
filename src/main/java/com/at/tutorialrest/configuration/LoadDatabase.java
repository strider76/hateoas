package com.at.tutorialrest.configuration;

import com.at.tutorialrest.model.Employee;
import com.at.tutorialrest.model.Order;
import com.at.tutorialrest.model.Status;
import com.at.tutorialrest.repository.EmployeeRepository;
import com.at.tutorialrest.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase (EmployeeRepository repository, OrderRepository orderRepository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo Bolson", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Bolson", "thief")));

            log.info("Preloading: " + orderRepository.save(new Order("MackBook Pro", Status.COMPLETED)));
            log.info("Preloading: " + orderRepository.save(new Order("MackBook Pro", Status.IN_PROGRESS)));

        };
    }

}
