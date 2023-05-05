package com.chemaev.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    private String email;

    @Size(min = 8, max = 63, message = "Password should contains from 8 to 63 symbols")
    private String password;
}
