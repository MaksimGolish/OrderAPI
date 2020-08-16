package com.example.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.persistence.model.Customer;
import com.example.persistence.model.CustomerModelAssembler;
import com.example.persistence.repo.CustomerRepository;
import com.example.web.exceptions.OrderNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerModelAssembler assembler;

    public CustomerController(CustomerRepository customerRepository,
                              CustomerModelAssembler assembler) {
        this.customerRepository = customerRepository;
        this.assembler = assembler;
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer>> getAllCustomers() {
        List<EntityModel<Customer>> allEmployees = customerRepository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(allEmployees,
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
    }

    @GetMapping("/customers/{id}")
    public EntityModel<Customer> getById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return assembler.toModel(customer);
    }

    @PostMapping("/customers")
    ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) {
        EntityModel<Customer> employeeEntityModel = assembler
                .toModel(customerRepository.save(newCustomer));

        return ResponseEntity
                .created(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

    @PutMapping("/customers/{id}")
    ResponseEntity<?> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        Customer updatedCustomer = customerRepository.findById(id)
                .map(employee -> {
                    employee.setName(newCustomer.getName());
                    employee.setPhone(newCustomer.getPhone());
                    return customerRepository.save(employee);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
        EntityModel<Customer> employeeEntityModel = assembler.toModel(updatedCustomer);
        return ResponseEntity
                .created(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

    @DeleteMapping("/customers/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
