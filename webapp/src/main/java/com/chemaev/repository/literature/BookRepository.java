package com.chemaev.repository.literature;

import com.chemaev.model.literature.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByIdIn(List<Integer> ids);
}
