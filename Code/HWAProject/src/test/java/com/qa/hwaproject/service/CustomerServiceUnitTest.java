package com.qa.hwaproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.repository.CustomerRepo;


@SpringBootTest
public class CustomerServiceUnitTest {

  @Autowired
  private CustomerService service;

  @MockBean
  private CustomerRepo repo;


  @Test
  void testCreate() {

    final Customer INPUT = new Customer("Tim", "Timson", "Timson1");
    final Customer OUTPUT = new Customer(1L, "Tim", "Timson", "Timson1");

    // WHEN
    Mockito.when(this.repo.saveAndFlush(INPUT)).thenReturn(OUTPUT);

    // THEN
    Assertions.assertThat(this.service.create(INPUT)).isEqualTo(OUTPUT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(INPUT);

  }

  @Test
  public void testReadOne() {


    final Customer SAVED_CUSTOMER = new Customer(1L, "Bob", "Bobson", "Bobson1");

    // WHEN
    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(SAVED_CUSTOMER));

    // THEN
    Assertions.assertThat(this.service.getById(SAVED_CUSTOMER.getId())).isEqualTo(SAVED_CUSTOMER);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findById(SAVED_CUSTOMER.getId());

  }

  @Test
  void testReadAll() {

    List<Customer> newList = new ArrayList<>();
    final Customer SAVED_CUSTOMER = new Customer(1L, "Bob", "Bobson", "Bobson1");
    newList.add(SAVED_CUSTOMER);

    // WHEN
    Mockito.when(this.repo.findAll()).thenReturn(newList);

    // THEN
    Assertions.assertThat(this.service.getAll()).isEqualTo(newList);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findAll();

  }

  @Test
  void testUpdate() {

    final Customer OLD_CUSTOMER = new Customer(1L, "Bob", "Bobson", "Bobson1");
    final Customer NEW_CUSTOMER = new Customer("Jim", "Jimson", "Jimson1");
    final Customer NEW_CUSTOMER_ID = new Customer(1L, "Jim", "Jimson", "Jimson1");
    // Optional<Customer> existingOptional = this.repo.findById(NEW_Customer.getId());

    // WHEN

    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(OLD_CUSTOMER));



    Mockito.when(this.repo.saveAndFlush(NEW_CUSTOMER)).thenReturn(NEW_CUSTOMER_ID);

    Customer returnedCustomer = service.update(1L, NEW_CUSTOMER);

    // THEN
    Assertions.assertThat(returnedCustomer = NEW_CUSTOMER_ID);
    // Assertions.assertThat(this.service.update(1L, NEW_Customer)).isEqualTo(NEW_Customer);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(NEW_CUSTOMER_ID);
  }

  @Test
  void testDelete() {

    // WHEN

    Mockito.when(this.repo.existsById(1L)).thenReturn(false);



    // THEN
    Assertions.assertThat(this.service.delete(1L)).isEqualTo(true);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
    Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
  }

  @Test
  void testDeleteFail() {

    // WHEN

    Mockito.when(this.repo.existsById(2L)).thenReturn(true);



    // THEN
    Assertions.assertThat(this.service.delete(2L)).isEqualTo(false);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).deleteById(2L);
    Mockito.verify(this.repo, Mockito.times(1)).existsById(2L);
  }

}
