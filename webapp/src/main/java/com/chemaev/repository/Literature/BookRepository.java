package com.chemaev.repository.Literature;

import com.chemaev.model.Literature.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book getBookByName(String name);

    Page<Book> findAll(Pageable pageable);
}
