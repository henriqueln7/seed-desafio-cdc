package com.estudos.apicdc.usecases.registerbook;

import com.estudos.apicdc.domain.Author;
import com.estudos.apicdc.domain.Book;
import com.estudos.apicdc.domain.Category;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {
    @NotBlank
    // Eh unico
    private String title;
    @Size(max = 500)
    @NotBlank
    private String resume;
    private String summaryMarkdown;
    @NotNull
    @Min(20)
    private BigDecimal price;
    @NotNull
    @Min(100)
    private int numberPages;
    @NotBlank
    //Eh unico
//    @ISBN
    private String isbn;
    @NotNull
    @Future
    private LocalDate launchDate;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long authorId;

    public NewBookRequest(@NotBlank String title, @NotBlank @Size(max = 500) String resume, String summaryMarkdown, @NotNull @Min(20) BigDecimal price, @NotNull @Min(100) int numberPages, @NotBlank String isbn, @Future LocalDate launchDate, @NotNull Long categoryId, @NotNull Long authorId) {
        this.title = title;
        this.resume = resume;
        this.summaryMarkdown = summaryMarkdown;
        this.price = price;
        this.numberPages = numberPages;
        this.isbn = isbn;
        this.launchDate = launchDate;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    protected Book toModel(EntityManager manager) {
        Category category = manager.find(Category.class, this.categoryId);
        Author author = manager.find(Author.class, this.authorId);

        Assert.notNull(category, "You should pass a valid categoryId");
        Assert.notNull(author, "You should pass a valid authorId");

        return new Book(this.title, this.resume, this.summaryMarkdown, this.price, this.numberPages, this.isbn, this.launchDate, category, author);
    }
}
