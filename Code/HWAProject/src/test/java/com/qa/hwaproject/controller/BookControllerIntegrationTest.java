package com.qa.hwaproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hwaproject.domain.Book;
import com.qa.hwaproject.dto.BookWithUsernameDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // tells Spring to setup the mockmvc object
@ActiveProfiles("test") // sets the active profile to 'test'
// runs the sql files before each test method
@Sql(scripts = {"classpath:book-schema.sql", "classpath:book-data.sql"},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class BookControllerIntegrationTest {


  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void createTest() throws Exception {
    Book entry = new Book("Dickens", "Charles", "A Christmas Carol", false);
    Book result = new Book(2L, "Dickens", "Charles", "A Christmas Carol", false);


    String entryAsJSON = this.mapper.writeValueAsString(entry);
    String resultAsJSON = this.mapper.writeValueAsString(result);

    RequestBuilder request =
        post("/books/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON);

    ResultMatcher checkStatus = status().isCreated();
    ResultMatcher checkBody = content().json(resultAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testGet() throws Exception {
    BookWithUsernameDTO hamlet =
        new BookWithUsernameDTO(1L, "Shakespeare", "William", "Hamlet", false, "no customer");
    String hamletAsJSON = this.mapper.writeValueAsString(hamlet);
    RequestBuilder request = get("/books/getById/1");

    ResultMatcher checkStatus = status().isOk();

    ResultMatcher checkBody = content().json(hamletAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testGetAll() throws Exception {
    BookWithUsernameDTO hamlet =
        new BookWithUsernameDTO(1L, "Shakespeare", "William", "Hamlet", false, "no customer");
    String hamletAsJSON = this.mapper.writeValueAsString(List.of(hamlet));
    RequestBuilder request = get("/books/getAll");

    ResultMatcher checkStatus = status().isOk();

    ResultMatcher checkBody = content().json(hamletAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testUpdate() throws Exception {
    Book pap = new Book("Austen", "Jane", "Pride and Prejudice", false);
    String papAsJSON = this.mapper.writeValueAsString(pap);
    RequestBuilder request =
        put("/books/update/1").contentType(MediaType.APPLICATION_JSON).content(papAsJSON);

    ResultMatcher checkStatus = status().isAccepted(); // matcher that we will use to test the
                                                       // response

    Book papSaved = new Book(1L, "Austen", "Jane", "Pride and Prejudice", false);
    String meSavedAsJSON = this.mapper.writeValueAsString(papSaved);

    ResultMatcher checkBody = content().json(meSavedAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

  }

  @Test
  void testDelete() throws Exception {
    this.mvc.perform(delete("/books/delete/1")).andExpect(status().isNoContent());
  }


}
