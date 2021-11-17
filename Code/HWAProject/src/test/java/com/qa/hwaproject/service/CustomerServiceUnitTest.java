package com.qa.hwaproject.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.repository.CustomerRepo;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceUnitTest {

  @InjectMocks
  private CustomerService service;

  @Mock
  private CustomerRepo repo;

  @Test
  public void createTest() {
    Customer input = new Customer("Tim", "Timson", "Timson1");
    Customer output = new Customer(1L, "Tim", "Timson", "Timson1");

    Mockito.when(this.repo.save(input)).thenReturn(output);

    assertEquals(output, this.service.create(input));

    Mockito.verify(this.repo, Mockito.times(1)).save(input);
  }

}
