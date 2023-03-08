package com.chemaev.dto.literature;

import com.chemaev.model.literature.Author;
import com.chemaev.model.literature.Book;
import com.chemaev.model.literature.Publisher;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponseDto {
    private Integer id;
    private String name;
    private Author author;
    private List<Publisher> publishers;

    public static BookResponseDto fromEntity(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .build();
    }
}
