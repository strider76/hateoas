package com.at.tutorialrest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.at.tutorialrest.model.Order;
import com.at.tutorialrest.model.Status;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>> {
    @Override
    public Resource<Order> toResource(Order order) {
        Resource<Order> orderResource = new Resource<>(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders")
                );

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderResource.add(
                    linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"),
                    linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete")
            );
        }
        return orderResource;
    }
}
