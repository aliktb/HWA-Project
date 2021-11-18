package com.qa.hwaproject.dto;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.qa.hwaproject.domain.Book;

@SpringBootTest
public class BookWithUsernameUnitTest {



  @Test
  void testToString() {

    BookWithUsernameDTO dtoObj = new BookWithUsernameDTO();

    Book book = new Book(1L, "Shakespeare", "William", "Hamlet", false);

    dtoObj.setId(book.getId());
    dtoObj.setAuthorFirstName(book.getAuthorFirstName());
    dtoObj.setAuthorLastName(book.getAuthorLastName());
    dtoObj.setBookTitle(book.getBookTitle());
    dtoObj.setCheckedOut(book.isCheckedOut());
    dtoObj.setUsername("no customer");



    String expected = "BookWithUsernameDTO [id=" + book.getId() + ", authorLastName="
        + book.getAuthorLastName() + ", authorFirstName=" + book.getAuthorFirstName()
        + ", bookTitle=" + book.getBookTitle() + ", checkedOut=" + book.isCheckedOut()
        + ", username=" + "no customer" + "]";

    System.out.println(dtoObj.toString());
    System.out.println(expected);

    Assert.assertTrue(dtoObj.toString().equals(expected));


  }
}
