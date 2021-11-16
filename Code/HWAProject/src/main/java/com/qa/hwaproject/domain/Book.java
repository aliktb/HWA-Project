package com.qa.hwaproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author_last_name")
  private String authorLastName;

  @Column(name = "author_first_name")
  private String authorFirstName;

  @Column(name = "book_title")
  private String bookTitle;

  @Column(name = "checked_out")
  private Boolean checkedOut;

  @Column(name = "customer_id")
  private Long customerCheckedId;

  @ManyToOne
  private Book book;

  public Book(String authorLastName, String authorFirstName, String bookTitle, Boolean checkedOut,
      Long customerCheckedId, Book book) {
    super();
    this.authorLastName = authorLastName;
    this.authorFirstName = authorFirstName;
    this.bookTitle = bookTitle;
    this.checkedOut = checkedOut;
    this.customerCheckedId = customerCheckedId;
    this.book = book;
  }


}
