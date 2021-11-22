package com.qa.hwaproject.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.hwaproject.domain.Book;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.dto.BookWithUsernameDTO;
import com.qa.hwaproject.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {

  private BookService service;

  // Constructor injection
  public BookController(BookService service) {
    this.service = service;
  }

  // Create
  @PostMapping("/create")
  public ResponseEntity<Book> create(@RequestBody Book book) {
    return new ResponseEntity<Book>(this.service.create(book), HttpStatus.CREATED);
  }

  // Read
  @GetMapping("/getAll")
  public ResponseEntity<List<BookWithUsernameDTO>> getAll() {
    return new ResponseEntity<List<BookWithUsernameDTO>>(this.service.getAll(), HttpStatus.OK);
  }

  // Read by ID
  @GetMapping("/getById/{id}")
  public ResponseEntity<BookWithUsernameDTO> getOne(@PathVariable Long id) {
    return new ResponseEntity<BookWithUsernameDTO>(this.service.getById(id), HttpStatus.OK);
  }

  // Update
  @PutMapping("/update/{id}")
  public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
    return new ResponseEntity<Book>(this.service.update(id, book), HttpStatus.ACCEPTED);
  }

  // Checkout Book
  @PutMapping("/checkout/{id}")
  public ResponseEntity<Book> checkout(@PathVariable Long id, @RequestBody Customer customer) {
    return new ResponseEntity<Book>(this.service.checkoutBook(id, customer), HttpStatus.ACCEPTED);
  }

  // Return Book
  @PutMapping("/return/{id}")
  public ResponseEntity<Book> returnBook(@PathVariable Long id) {
    return new ResponseEntity<Book>(this.service.returnBook(id), HttpStatus.ACCEPTED);
  }

  // Get books by username
  @GetMapping("/getBooksByUsername/{username}")
  public ResponseEntity<List<BookWithUsernameDTO>> getBooksByUsername(
      @PathVariable String username) {

    return new ResponseEntity<List<BookWithUsernameDTO>>(this.service.getBooksByUsername(username),
        HttpStatus.OK);
  }


  // Delete
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Book> delete(@PathVariable Long id) {
    return this.service.delete(id) ? new ResponseEntity<Book>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
