package com.at.tutorialrest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.at.tutorialrest.exception.EmployeeNotFoundException;
import com.at.tutorialrest.model.Employee;
import com.at.tutorialrest.repository.EmployeeRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeResourceAssembler employeeResourceAssembler;

    public EmployeeController(EmployeeRepository repository, EmployeeResourceAssembler employeeResourceAssembler) {
        this.repository = repository;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    @GetMapping("/employees")
    Resources<Resource<Employee>> all() {
        List<Resource<Employee>> employees = repository.findAll().stream()
                .map(employeeResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(employees,
                    linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @GetMapping("/employees/{id}")
    Resource<Employee> one (@PathVariable Long id) {
        Employee employee = repository.findById(id)
                                .orElseThrow(() ->new EmployeeNotFoundException(id));
        return employeeResourceAssembler.toResource(employee);
    }

    @PostMapping("/employees")
    ResponseEntity<?>  newEmployee (@RequestBody Employee newEmployee) throws URISyntaxException {
        Resource<Employee> resource = this.employeeResourceAssembler.toResource(repository.save(newEmployee));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee (@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException {
        Employee updatedEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(()->{
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
        Resource<Employee> resource = employeeResourceAssembler.toResource(updatedEmployee);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/employee/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
