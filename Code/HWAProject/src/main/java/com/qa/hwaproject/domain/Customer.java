package com.qa.hwaproject.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "username", unique = true, nullable = false)
  private String username;


  @JsonIgnore
  @OneToMany(mappedBy = "customer")
  private List<Book> lists;

  public Customer(String firstName, String lastName, String username) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
  }

  public Customer(Long id, String firstName, String lastName, String username) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;

  }



}
