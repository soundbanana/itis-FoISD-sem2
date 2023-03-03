package com.chemaev.controllers;

import com.chemaev.model.Literature.Author;
import com.chemaev.repository.Literature.AuthorRepository;
import com.chemaev.services.Literature.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    // TODO add other services here!

    @PostMapping(value = "/addAuthor",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Author createAuthor(@Valid @RequestBody Author newAuthor) {
        return authorService.createAuthor(newAuthor);
    }
}