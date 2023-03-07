package com.chemaev.services.literature.impl;

import com.chemaev.model.literature.Author;
import com.chemaev.repository.literature.AuthorRepository;
import com.chemaev.services.literature.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author newAuthor) {
        return authorRepository.save(
                Author.builder()
                        .name(newAuthor.getName().trim())
                        .books(newAuthor.getBooks())
                        .build()
        );
    }
}
