package com.chemaev.dto.literature;

import com.chemaev.model.literature.Author;
import com.chemaev.model.literature.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthorResponseDto {
    private Integer id;
    private String name;
    private List<Book> books;

    public static AuthorResponseDto fromEntity(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .books(author.getBooks())
                .build();
    }
}
