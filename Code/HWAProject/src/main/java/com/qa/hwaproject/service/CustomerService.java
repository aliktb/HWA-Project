package com.qa.hwaproject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.repository.CustomerRepo;

@Service
public class CustomerService {

  private CustomerRepo repo;

  public CustomerService(CustomerRepo repo) {
    this.repo = repo;
  }

  // Create
  public Customer create(Customer customer) {
    return this.repo.saveAndFlush(customer);
  }

  // Read All
  public List<Customer> getAll() {
    return this.repo.findAll();
  }

  // Read by ID
  public Customer getById(Long id) {
    return this.repo.findById(id).get();
  }

  // Update
  public Customer update(Long id, Customer customer) {
    Customer existing = this.repo.findById(id).get();
    existing.setFirstName(customer.getFirstName());
    existing.setLastName(customer.getLastName());
    existing.setUsername(customer.getUsername());
    return this.repo.saveAndFlush(existing);
  }

  // Delete
  public boolean delete(Long id) {
    this.repo.deleteById(id);
    return !this.repo.existsById(id);
  }


}
