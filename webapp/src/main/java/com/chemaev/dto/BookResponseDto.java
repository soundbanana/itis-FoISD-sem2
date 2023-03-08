package com.chemaev.dto;

import com.chemaev.dto.literature.AuthorResponseDto;
import com.chemaev.model.literature.Author;
import com.chemaev.model.literature.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDto {
    private Integer id;
    private String name;
    private Author author;

    public static BookResponseDto fromEntity(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .build();
    }
}
