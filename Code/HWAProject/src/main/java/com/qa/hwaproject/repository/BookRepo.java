package com.qa.hwaproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.qa.hwaproject.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

  @Query(nativeQuery = true,
      value = "SELECT * FROM book AS bo JOIN customer AS cu ON bo.customer_id = cu.id WHERE cu.username = ?1")
  List<Book> findBooksByUsername(String username);


}
