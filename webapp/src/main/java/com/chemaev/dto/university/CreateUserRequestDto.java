package com.chemaev.dto.university;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class CreateUserRequestDto {
    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    @Email
    private String email;

    @Past(message = "Birthdate should be real")
    @DateTimeFormat(pattern = "YYY-MM-DD")
    private LocalDate birthday;
    @Size(min = 8, max = 64, message = "Password should contain from 8 to 64 symbols")
    private String password;
}
