package com.estudos.apicdc.domain;

import com.estudos.apicdc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);
}
