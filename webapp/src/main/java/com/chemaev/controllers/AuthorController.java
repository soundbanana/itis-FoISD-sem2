package com.chemaev.controllers;

import com.chemaev.dto.literature.AuthorResponseDto;
import com.chemaev.dto.literature.CreateAuthorRequestDto;
import com.chemaev.services.literature.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    // TODO add other services here!

    @PostMapping(value = "/addAuthor",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponseDto createAuthor(@Valid @RequestBody CreateAuthorRequestDto newAuthor) {
        return authorService.createAuthor(newAuthor);
    }

    @GetMapping(value = {"/authors/", "authors"})
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.findAll();
    }
}