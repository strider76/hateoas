package com.at.tutorialrest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.at.tutorialrest.controller.EmployeeController;
import com.at.tutorialrest.model.Employee;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource<Employee>> {

    @Override
    public Resource<Employee> toResource(Employee employee) {
        return new Resource<>(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
                );
    }

}
