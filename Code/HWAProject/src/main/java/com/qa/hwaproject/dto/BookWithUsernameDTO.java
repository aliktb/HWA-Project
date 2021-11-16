package com.qa.hwaproject.dto;

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



}
