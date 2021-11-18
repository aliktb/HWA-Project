package com.qa.hwaproject.dto;

import java.util.Objects;

public class BookWithUsernameDTO {

  private Long id;
  private String authorLastName;
  private String authorFirstName;
  private String bookTitle;
  private Boolean checkedOut;
  private String username;


  public BookWithUsernameDTO(Long id, String authorLastName, String authorFirstName,
      String bookTitle, Boolean checkedOut, String username) {
    super();
    this.id = id;
    this.authorLastName = authorLastName;
    this.authorFirstName = authorFirstName;
    this.bookTitle = bookTitle;
    this.checkedOut = checkedOut;
    this.username = username;
  }


  public BookWithUsernameDTO() {
    super();
  }


  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public String getAuthorLastName() {
    return authorLastName;
  }


  public void setAuthorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
  }


  public String getAuthorFirstName() {
    return authorFirstName;
  }


  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }


  public String getBookTitle() {
    return bookTitle;
  }


  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }


  public Boolean getCheckedOut() {
    return checkedOut;
  }


  public void setCheckedOut(Boolean checkedOut) {
    this.checkedOut = checkedOut;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public String toString() {
    return "BookWithUsernameDTO [id=" + id + ", authorLastName=" + authorLastName
        + ", authorFirstName=" + authorFirstName + ", bookTitle=" + bookTitle + ", checkedOut="
        + checkedOut + ", username=" + username + "]";
  }


  @Override
  public int hashCode() {
    return Objects.hash(authorFirstName, authorLastName, bookTitle, checkedOut, id, username);
  }


  @Override
  public boolean equals(Object obj) {

    BookWithUsernameDTO other = (BookWithUsernameDTO) obj;
    return Objects.equals(authorFirstName, other.authorFirstName)
        && Objects.equals(authorLastName, other.authorLastName)
        && Objects.equals(bookTitle, other.bookTitle)
        && Objects.equals(checkedOut, other.checkedOut) && Objects.equals(id, other.id)
        && Objects.equals(username, other.username);
  }



}
