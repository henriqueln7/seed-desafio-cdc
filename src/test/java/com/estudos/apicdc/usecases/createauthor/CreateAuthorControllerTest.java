package com.estudos.apicdc.usecases.createauthor;

import com.estudos.apicdc.domain.Author;
import com.estudos.apicdc.domain.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateAuthorControllerTest {

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorRepository authorRepository;


    @Test
    @DisplayName("It should save user with valid name, description and email")
    void itShouldSaveUserWithValidNameDescriptionAndEmail() throws Exception {
        CreateAuthorRequest request = new CreateAuthorRequest("Ednaldo Pereira",
                                                              "Ednaldo Pereira o maior cantor do brasil",
                                                              "ednaldo@pereira.com");
        mvc.perform(post("/authors")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
           .andExpect(status().isCreated());
        Mockito.verify(authorRepository, Mockito.times(1))
               .save(Mockito.any(Author.class)); // Remove
    }

    @ParameterizedTest
    @CsvSource({
            " , maior autor do brasil, autor@gmail.com",
            "autor, , autor@gmail.com",
            "autor, maior autor, ,",
            "autor, maior autor do brasil, autorcom"
    })
    @DisplayName("It should thrown exception if author has invalid data")
    void itShouldThrownExceptionIfAuthorHasInvalidData(String name, String description, String email) throws Exception {
        CreateAuthorRequest request = new CreateAuthorRequest(name, description, email);
        mvc.perform(post("/authors")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
           .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("It should return status 400 if author with same email already exists")
    void itShouldReturnStatus400IfAuthorWithSameEmailAlreadyExists() throws Exception {
        final String authorEmail = "author@gmail.com";
        Mockito.when(authorRepository.existsByEmail(authorEmail))
               .thenReturn(true);
        CreateAuthorRequest request = new CreateAuthorRequest("Author", "Nice author", authorEmail);
        mvc.perform(post("/authors").contentType(MediaType.APPLICATION_JSON)
                                    .content(mapper.writeValueAsString(request)))
           .andExpect(status().isBadRequest());
    }
}