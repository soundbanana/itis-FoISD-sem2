package com.chemaev.repository.literature;

import com.chemaev.model.literature.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book getBookByName(String name);

    Page<Book> findAll(Pageable pageable);
}
