package com.qa.hwaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.hwaproject.domain.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
