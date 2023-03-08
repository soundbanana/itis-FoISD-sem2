package com.chemaev.services.literature;

import com.chemaev.dto.literature.AuthorResponseDto;
import com.chemaev.dto.literature.CreateAuthorRequestDto;
import com.chemaev.model.literature.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    AuthorResponseDto createAuthor(CreateAuthorRequestDto newAuthor);

    List<AuthorResponseDto> findAll();
}
