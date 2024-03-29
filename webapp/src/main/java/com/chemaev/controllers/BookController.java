package com.chemaev.controllers;

import com.chemaev.dto.literature.CreateBookRequestDto;
import com.chemaev.dto.literature.BookResponseDto;
import com.chemaev.model.literature.Book;
import com.chemaev.services.literature.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(value = "/addBook",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponseDto createBook(@Valid @RequestBody CreateBookRequestDto newBook) {
        return bookService.createBook(newBook);
    }

    @GetMapping(value = {"/books/", "books"})
    public String getAllBooks(Model model) {
        model.addAttribute("booksList", bookService.findAll());
        return "books";
    }
}
