package com.qa.hwaproject.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  private CustomerService service;

  // Constructor injection
  public CustomerController(CustomerService service) {
    this.service = service;
  }

  // CREATE
  @PostMapping("/create")
  public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    return new ResponseEntity<Customer>(this.service.create(customer), HttpStatus.CREATED);
  }

  // READ ALL
  @GetMapping("/getAll")
  public ResponseEntity<List<Customer>> getAll() {
    return new ResponseEntity<List<Customer>>(this.service.getAll(), HttpStatus.OK);
  }

  // READ BY ID
  @GetMapping("/getById/{id}")
  public ResponseEntity<Customer> getOne(@PathVariable Long id) {
    return new ResponseEntity<Customer>(this.service.getById(id), HttpStatus.OK);
  }

  // Update
  @PutMapping("/update/{id}")
  public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
    return new ResponseEntity<Customer>(this.service.update(id, customer), HttpStatus.ACCEPTED);
  }



  // Delete
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Customer> delete(@PathVariable Long id) {
    return this.service.delete(id) ? new ResponseEntity<Customer>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
