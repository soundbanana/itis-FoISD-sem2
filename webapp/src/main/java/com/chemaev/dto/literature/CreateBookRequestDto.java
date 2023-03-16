package com.chemaev.dto.literature;

import com.chemaev.model.literature.Author;
import com.chemaev.model.literature.Publisher;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "Name shouldn't be blank")
    private String name;
    private Author author;
    private List<Publisher> publishers;
}
