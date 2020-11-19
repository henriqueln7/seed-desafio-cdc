package com.estudos.apicdc.usecases.registerbook;

import com.estudos.apicdc.domain.Author;
import com.estudos.apicdc.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NewBookRequestTest {

    private NewBookRequest request = new NewBookRequest("", "", "", new BigDecimal("30"), 20, "", LocalDate.now(), 1L, 1L);


    @Test
    @DisplayName("It should create a book when category and author exists")
    void itShouldCreateABookWhenCategoryAndAuthorExists() {

        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Author.class, 1L)).thenReturn(new Author("Author", "Author description", "author@email.com"));
        Mockito.when(manager.find(Category.class, 1L)).thenReturn(new Category("Category"));

        assertThatCode(() -> request.toModel(manager)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("It should not create a book when category does not exist and author exists")
    void itShouldNotCreateABookWhenCategoryDoesNotExistAndAuthorExists() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Author.class, 1L)).thenReturn(new Author("Author", "Author description", "author@email.com"));
        Mockito.when(manager.find(Category.class, 1L)).thenReturn(null);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            request.toModel(manager);
        });
    }

    @Test
    @DisplayName("It should not create a book when category exists and author does not exist")
    void itShouldNotCreateABookWhenCategoryExistsAndAuthorDoesNotExist() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Author.class, 1L)).thenReturn(null);
        Mockito.when(manager.find(Category.class, 1L)).thenReturn(new Category("Category"));

        assertThatIllegalArgumentException().isThrownBy(() -> {
            request.toModel(manager);
        });

    }

    @Test
    @DisplayName("It should not create a book when category and author don't exist")
    void itShouldNotCreateABookWhenCategoryAndAuthorDontExist() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Author.class, 1L)).thenReturn(null);
        Mockito.when(manager.find(Category.class, 1L)).thenReturn(null);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            request.toModel(manager);
        });

    }
}