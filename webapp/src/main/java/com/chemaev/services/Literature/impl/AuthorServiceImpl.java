package com.chemaev.services.Literature.impl;

import com.chemaev.model.Literature.Author;
import com.chemaev.model.University.User;
import com.chemaev.repository.Literature.AuthorRepository;
import com.chemaev.services.Literature.AuthorService;
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
