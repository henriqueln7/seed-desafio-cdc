package com.estudos.apicdc.usecases.querybook;

import com.estudos.apicdc.domain.Author;
import com.estudos.apicdc.domain.Book;

import java.math.BigDecimal;

public class DetailBookResponse {

    public final String title;
    public final String resume;
    public final String summaryMarkdown;
    public final int numberPages;
    public final String isbn;
    public final BigDecimal price;
    public final AuthorDetailResponse author;

    public DetailBookResponse(Book book) {
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.summaryMarkdown = book.getSummaryMarkdown();
        this.numberPages = book.getNumberPages();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
        this.author = new AuthorDetailResponse(book.getAuthor());
    }

    private class AuthorDetailResponse {

        public final String name;
        public final String description;

        public AuthorDetailResponse(Author author) {
            this.name = author.getName();
            this.description = author.getDescription();
        }
    }
}
