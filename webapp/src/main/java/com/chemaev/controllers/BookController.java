//package com.chemaev.controllers;
//
//import com.chemaev.dto.BookResponseDto;
//import com.chemaev.dto.literature.AuthorResponseDto;
//import com.chemaev.model.literature.Book;
//import com.chemaev.repository.literature.BookRepository;
//import com.chemaev.services.literature.BookService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//public class BookController {
//    private final BookRepository bookRepository;
//    private final BookService bookService;
//
//    @PostMapping(value = "/addBook",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public BookResponseDto createBook(@Valid @RequestBody Book newBook) {
//        return bookService.createBook(newBook);
//    }
//
//    @GetMapping(value = {"/books/", "books"})
//    public List<Book> getAllBooks() {
//        return bookService.findAll();
//    }
//}
