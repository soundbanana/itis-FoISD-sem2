package com.chemaev.services.literature.impl;

import com.chemaev.dto.literature.AuthorResponseDto;
import com.chemaev.dto.literature.CreateAuthorRequestDto;
import com.chemaev.model.literature.Author;
import com.chemaev.repository.literature.AuthorRepository;
import com.chemaev.services.literature.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto createAuthor(CreateAuthorRequestDto newAuthor) {
        return AuthorResponseDto.fromEntity(authorRepository.save(
                Author.builder()
                        .name(newAuthor.getName().trim())
                        .books(newAuthor.getBooks())
                        .build()
        ));
    }

    @Override
    public List<AuthorResponseDto> findAll() {
        return authorRepository.findAll().stream().map(author -> AuthorResponseDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .books(author.getBooks())
                        .build())
                .collect(Collectors.toList());
    }
}
