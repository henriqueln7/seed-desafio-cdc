package com.estudos.apicdc.usecases.querybook;

import com.estudos.apicdc.domain.Book;
import com.estudos.apicdc.domain.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Como as operações de fazer query em um livro sempre são bastante semelhantes e não envolvem nenhuma lógica ultra especial, decidi deixar-elas dentro desse controller. Aqui os métodos são apenas de "leitura", não modificam o sistema.
 */
@RestController
public class QueryBooksController {
    private final BookRepository bookRepository;

    public QueryBooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<BookResponse> listBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    public DetailBookResponse detailBook(@PathVariable("id") Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " does not exist");
        }

        Book book = optionalBook.get();
        return new DetailBookResponse(book);
    }
}
