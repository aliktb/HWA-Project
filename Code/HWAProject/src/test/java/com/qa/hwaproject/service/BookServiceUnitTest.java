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
import com.qa.hwaproject.domain.Book;
import com.qa.hwaproject.domain.Customer;
import com.qa.hwaproject.dto.BookWithUsernameDTO;
import com.qa.hwaproject.repository.BookRepo;

@SpringBootTest
public class BookServiceUnitTest {


  @Autowired
  private BookService service;



  @MockBean
  private BookRepo repo;

  @Test
  void testCreate() {

    final Book INPUT = new Book("Lee", "Harper", "To Kill a Mockingbird", false);
    final Book OUTPUT = new Book(1L, "Lee", "Harper", "To Kill a Mockingbird", false);

    // WHEN
    Mockito.when(this.repo.saveAndFlush(INPUT)).thenReturn(OUTPUT);

    // THEN
    Assertions.assertThat(this.service.create(INPUT)).isEqualTo(OUTPUT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(INPUT);

  }


  @Test
  public void testReadOne() {


    final BookWithUsernameDTO SAVED_BOOK_DTO =
        new BookWithUsernameDTO(1L, "Shakespeare", "William", "Hamlet", false, "no customer");

    final Book SAVED_BOOK = new Book(1L, "Shakespeare", "William", "Hamlet", false);


    // WHEN
    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(SAVED_BOOK));



    // THEN
    Assertions.assertThat(this.service.getById(SAVED_BOOK.getId())).isEqualTo(SAVED_BOOK_DTO);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findById(SAVED_BOOK.getId());

  }


  @Test
  void testReadAll() {

    List<Book> newList = new ArrayList<>();
    List<BookWithUsernameDTO> newListDTO = new ArrayList<>();
    final Book SAVED_BOOK = new Book(1L, "Shakespeare", "William", "Hamlet", false);
    final BookWithUsernameDTO SAVED_BOOK_DTO =
        new BookWithUsernameDTO(1L, "Shakespeare", "William", "Hamlet", false, "no customer");
    newList.add(SAVED_BOOK);
    newListDTO.add(SAVED_BOOK_DTO);

    // WHEN
    Mockito.when(this.repo.findAll()).thenReturn(newList);

    // THEN
    Assertions.assertThat(this.service.getAll()).isEqualTo(newListDTO);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findAll();

  }


  @Test
  void testUpdate() {

    final Book OLD_BOOK = new Book(1L, "Shakespeare", "William", "Hamlet", false);
    final Book NEW_BOOK = new Book("Shakespeare", "William", "Romeo & Juliet", false);
    final Book NEW_BOOK_ID = new Book(1L, "Shakespeare", "William", "Romeo & Juliet", false);
    // Optional<Customer> existingOptional = this.repo.findById(NEW_Customer.getId());

    // WHEN

    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(OLD_BOOK));



    Mockito.when(this.repo.saveAndFlush(NEW_BOOK)).thenReturn(NEW_BOOK_ID);

    Book returnedBook = service.update(1L, NEW_BOOK);

    // THEN
    Assertions.assertThat(returnedBook = NEW_BOOK_ID);
    // Assertions.assertThat(this.service.update(1L, NEW_Customer)).isEqualTo(NEW_Customer);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(NEW_BOOK_ID);
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

  @Test
  void testGetBooksByUsername() {

    List<Book> newList = new ArrayList<>();
    List<BookWithUsernameDTO> newListDTO = new ArrayList<>();
    final Book SAVED_BOOK = new Book(2L, "Shakespeare", "William", "Tempest", true,
        new Customer("Bob", "Bobson", "Bobson1"));
    final BookWithUsernameDTO SAVED_BOOK_DTO =
        new BookWithUsernameDTO(2L, "Shakespeare", "William", "Tempest", true, "Bobson1");
    newList.add(SAVED_BOOK);
    newListDTO.add(SAVED_BOOK_DTO);

    // WHEN

    Mockito.when(this.repo.findBooksByUsername("Bobson1")).thenReturn(newList);

    // THEN
    Assertions.assertThat(this.service.getBooksByUsername("Bobson1")).isEqualTo(newListDTO);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findBooksByUsername("Bobson1");

  }

  @Test
  void testCheckoutBook() {

    final Customer SAVED_CUSTOMER = new Customer("Bob", "Bobson", "Bobson1");
    final Book SAVED_BOOK = new Book(1L, "Shakespeare", "William", "Hamlet", false, null);
    final Book SAVED_BOOK_CHECKOUT =
        new Book(1L, "Shakespeare", "William", "Hamlet", true, SAVED_CUSTOMER);


    // WHEN
    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(SAVED_BOOK));
    Mockito.when(this.repo.saveAndFlush(SAVED_BOOK_CHECKOUT)).thenReturn(SAVED_BOOK_CHECKOUT);

    // THEN
    Assertions.assertThat(this.service.checkoutBook(1L, SAVED_CUSTOMER))
        .isEqualTo(SAVED_BOOK_CHECKOUT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(SAVED_BOOK_CHECKOUT);
  }


  @Test
  void testReturnBook() {

    final Customer SAVED_CUSTOMER = new Customer("Bob", "Bobson", "Bobson1");
    final Book SAVED_BOOK = new Book(1L, "Shakespeare", "William", "Hamlet", true, SAVED_CUSTOMER);
    final Book SAVED_BOOK_CHECKOUT = new Book(1L, "Shakespeare", "William", "Hamlet", false, null);



    // WHEN
    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(SAVED_BOOK));
    Mockito.when(this.repo.saveAndFlush(SAVED_BOOK_CHECKOUT)).thenReturn(SAVED_BOOK_CHECKOUT);

    // THEN
    Assertions.assertThat(this.service.returnBook(1L)).isEqualTo(SAVED_BOOK_CHECKOUT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(SAVED_BOOK_CHECKOUT);

  }


}
