package com.chemaev.repository.literature;

import com.chemaev.model.literature.Author;
import com.chemaev.model.literature.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Page<Author> findAllBy(List<Book> books, Pageable pageable);
//    List<Author> findAllById(List<Integer> ids);
}
