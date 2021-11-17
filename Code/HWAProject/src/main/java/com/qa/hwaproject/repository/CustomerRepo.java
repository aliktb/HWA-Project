package com.qa.hwaproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.qa.hwaproject.domain.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {



  @Query("SELECT c from Customer c WHERE username = ?1")
  List<Customer> findBooksByUsernameJPQL(String name);

}
