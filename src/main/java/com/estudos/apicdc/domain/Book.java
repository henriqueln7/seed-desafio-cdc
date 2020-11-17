package com.estudos.apicdc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @NotBlank
    @Size(max = 500)
    private String resume;
    private String summaryMarkdown;
    private BigDecimal price;
    private int numberPages;
    private String isbn;
    private LocalDate launchDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;

    @Deprecated
    public Book(){}

    public Book(String title, String resume, String summaryMarkdown, BigDecimal price, int numberPages, String isbn, LocalDate launchDate, Category category, Author author) {
        this.title = title;
        this.resume = resume;
        this.summaryMarkdown = summaryMarkdown;
        this.price = price;
        this.numberPages = numberPages;
        this.isbn = isbn;
        this.launchDate = launchDate;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSummaryMarkdown() {
        return summaryMarkdown;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }
}
