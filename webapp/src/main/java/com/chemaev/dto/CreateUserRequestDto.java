package com.chemaev.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequestDto {
    @NotBlank(message = "Name shouldn't be blank")
    private String name;
    @NotBlank(message = "Email shouldn't be blank")
    private String email;

}
