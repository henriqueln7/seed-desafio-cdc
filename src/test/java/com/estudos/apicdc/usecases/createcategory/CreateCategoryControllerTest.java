package com.estudos.apicdc.usecases.createcategory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateCategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();
    
    @Test
    @DisplayName("It should return 201 when a valid name is given")
    void itShouldReturn201WhenAValidNameIsGiven() throws Exception {
        CreateCategoryRequest request = new CreateCategoryRequest("valid");
        mvc.perform(post("/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
           .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @CsvSource({
            "''",
            "'     '"
    })
    @DisplayName("It should return status 400 when a invalid name is given")
    void itShouldReturnStatus400WhenAInvalidNameIsGiven(String name) throws Exception {
        CreateCategoryRequest request = new CreateCategoryRequest(name);
        mvc.perform(post("/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
           .andExpect(status().isBadRequest());
    }
}