package com.chemaev.dto.literature;

import com.chemaev.model.literature.Book;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateAuthorRequestDto {
    @NotBlank(message = "Name shouldn't be blank")
    private String name;
    private List<Book> books;
}
