package com.chemaev.services.literature;

import com.chemaev.dto.literature.CreateBookRequestDto;
import com.chemaev.dto.literature.BookResponseDto;
import com.chemaev.model.literature.Book;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {
    BookResponseDto createBook(CreateBookRequestDto newBook);
    List<Book> findAll();
}
