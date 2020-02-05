package com.chrisshayan.sample.hateoas;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    public void getShouldFetchAHalDocument() throws Exception {
        given(repository.findAll()).willReturn( //
                Arrays.asList( //
                        new Employee(1L,"Frodo", "Baggins", "ring bearer"), //
                        new Employee(2L,"Bilbo", "Baggins", "burglar")));

        mvc.perform(get("/employees").accept(MediaTypes.HAL_JSON_VALUE)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$._embedded.employees[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.employees[0].name", is("Frodo")))
                .andExpect(jsonPath("$._embedded.employees[0].surname", is("Baggins")))
                .andExpect(jsonPath("$._embedded.employees[0].role", is("ring bearer")))
                .andExpect(jsonPath("$._embedded.employees[0]._links.self.href", is("http://localhost/employees/1")))
                .andExpect(jsonPath("$._embedded.employees[0]._links.employees.href", is("http://localhost/employees")))
                .andExpect(jsonPath("$._embedded.employees[1].id", is(2)))
                .andExpect(jsonPath("$._embedded.employees[1].name", is("Bilbo")))
                .andExpect(jsonPath("$._embedded.employees[1].surname", is("Baggins")))
                .andExpect(jsonPath("$._embedded.employees[1].role", is("burglar")))
                .andExpect(jsonPath("$._embedded.employees[1]._links.self.href", is("http://localhost/employees/2")))
                .andExpect(jsonPath("$._embedded.employees[1]._links.employees.href", is("http://localhost/employees")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/employees"))) //
                .andReturn();
    }
}
