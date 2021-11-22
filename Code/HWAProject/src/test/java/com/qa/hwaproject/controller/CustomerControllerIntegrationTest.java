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
import com.qa.hwaproject.domain.Customer;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // tells Spring to setup the mockmvc object
@ActiveProfiles("test") // sets the active profile to 'test'
// runs the sql files before each test method
@Sql(scripts = {"classpath:customer-schema.sql", "classpath:customer-data.sql"},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CustomerControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;



  @Test
  public void createTest() throws Exception {
    Customer entry = new Customer("Tim", "Timson", "Timson1");
    Customer result = new Customer(2L, "Tim", "Timson", "Timson1");


    String entryAsJSON = this.mapper.writeValueAsString(entry);
    String resultAsJSON = this.mapper.writeValueAsString(result);

    RequestBuilder request =
        post("/customer/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON);

    ResultMatcher checkStatus = status().isCreated();
    ResultMatcher checkBody = content().json(resultAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testGet() throws Exception {
    Customer bob = new Customer(1L, "Bob", "Bobson", "Bobson1");
    String bobAsJSON = this.mapper.writeValueAsString(bob);
    RequestBuilder request = get("/customer/getById/1");

    ResultMatcher checkStatus = status().isOk();

    ResultMatcher checkBody = content().json(bobAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testGetAll() throws Exception {
    Customer bob = new Customer(1L, "Bob", "Bobson", "Bobson1");
    String customersJSON = this.mapper.writeValueAsString(List.of(bob));
    RequestBuilder request = get("/customer/getAll");

    ResultMatcher checkStatus = status().isOk();

    ResultMatcher checkBody = content().json(customersJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testUpdate() throws Exception {
    Customer jon = new Customer("Jon", "Jonson", "Jonson1");
    String meAsJSON = this.mapper.writeValueAsString(jon);
    RequestBuilder request =
        put("/customer/update/1").contentType(MediaType.APPLICATION_JSON).content(meAsJSON);

    ResultMatcher checkStatus = status().isAccepted(); // matcher that we will use to test the
                                                       // response

    Customer jonSaved = new Customer(1L, "Jon", "Jonson", "Jonson1");
    String meSavedAsJSON = this.mapper.writeValueAsString(jonSaved);

    ResultMatcher checkBody = content().json(meSavedAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

  }

  @Test
  void testDelete() throws Exception {
    this.mvc.perform(delete("/customer/delete/1")).andExpect(status().isNoContent());
  }



}
