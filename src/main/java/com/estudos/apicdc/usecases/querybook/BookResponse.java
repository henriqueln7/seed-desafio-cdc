package com.estudos.apicdc.usecases.querybook;

import com.estudos.apicdc.domain.Book;

public class BookResponse {
    public final Long id;
    public final String title;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }
}
