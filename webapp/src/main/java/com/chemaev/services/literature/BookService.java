package com.chemaev.services.literature;

import com.chemaev.model.literature.Book;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {
    Book createBook(Book newBook);
    List<Book> findAll();
}
