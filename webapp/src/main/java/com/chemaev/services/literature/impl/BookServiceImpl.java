package com.chemaev.services.literature.impl;

import com.chemaev.model.literature.Book;
import com.chemaev.repository.literature.BookRepository;
import com.chemaev.services.literature.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book createBook(Book newBook) {
        return bookRepository.save(
                Book.builder()
                        .name(newBook.getName().trim())
                        .author(newBook.getAuthor())
                        .publishers(newBook.getPublishers())
                        .build()
        );
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream().map(book -> Book.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .author(book.getAuthor())
                        .publishers(book.getPublishers())
                        .build())
                .collect(Collectors.toList());
    }
}
