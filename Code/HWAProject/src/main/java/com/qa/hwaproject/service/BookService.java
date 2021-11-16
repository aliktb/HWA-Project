package com.qa.hwaproject.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.qa.hwaproject.domain.Book;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.dto.BookWithUsernameDTO;
import com.qa.hwaproject.repository.BookRepo;

@Service
public class BookService {

  private BookRepo repo;

  public BookService(BookRepo repo) {
    this.repo = repo;
  }

  private BookWithUsernameDTO mapToDTO(Book book) {

    BookWithUsernameDTO dto = new BookWithUsernameDTO();

    dto.setId(book.getId());
    dto.setAuthorFirstName(book.getAuthorFirstName());
    dto.setAuthorLastName(book.getAuthorLastName());
    dto.setBookTitle(book.getBookTitle());
    dto.setCheckedOut(book.isCheckedOut());
    if (book.getCustomer() != null) {
      dto.setUsername(book.getCustomer().getUsername());
    } else {
      dto.setUsername("no customer");
    }


    return dto;

  }

  // CREATE
  public Book create(Book book) {
    return this.repo.saveAndFlush(book);
  }

  // READ ALL
  public List<BookWithUsernameDTO> getAll() {
    return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
  }

  // READ BY ID
  public BookWithUsernameDTO getById(Long id) {
    Book readBook = this.repo.findById(id).get();
    return mapToDTO(readBook);
  }

  // UPDATE
  public Book update(Long id, Book book) {
    Book existing = this.repo.findById(id).get();
    existing.setAuthorFirstName(book.getAuthorFirstName());
    existing.setAuthorLastName(book.getAuthorLastName());
    existing.setBookTitle(book.getBookTitle());
    existing.setCheckedOut(book.isCheckedOut());

    return this.repo.saveAndFlush(existing);
  }

  // Checkout book
  public Book checkoutBook(Long id, Customer customer) {
    Book toBeCheckedOut = this.repo.findById(id).get();
    toBeCheckedOut.setCustomer(customer);
    toBeCheckedOut.setCheckedOut(true);

    return this.repo.saveAndFlush(toBeCheckedOut);
  }

  // Return Book
  public Book returnBook(Long id) {

    Book toBeReturned = this.repo.findById(id).get();
    toBeReturned.setCustomer(null);
    toBeReturned.setCheckedOut(false);

    return this.repo.saveAndFlush(toBeReturned);
  }

  // DELETE
  public Boolean delete(Long id) {
    this.repo.deleteById(id);
    return !this.repo.existsById(id);
  }



}
