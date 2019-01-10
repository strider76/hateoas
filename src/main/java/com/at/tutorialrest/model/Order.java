package com.at.tutorialrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER_SERVICE")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Status status;

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}
