package com.qa.hwaproject.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.qa.hwaproject.domain.Book;
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
    dto.setBookTitle(book.getCustomer().getUsername());
    dto.setCheckedOut(book.isCheckedOut());

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
  public 



}
