package com.qa.hwaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.hwaproject.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
