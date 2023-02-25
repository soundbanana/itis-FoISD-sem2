package com.chemaev.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class CreateUserRequestDto {
    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    @Email
    private String email;

    @Past(message = "Birthdate should be real")
    @NotNull(message = "Birthdate shouldn't be null")
    @DateTimeFormat(pattern = "YYY-MM-DD")
    private LocalDate birthday;
}
